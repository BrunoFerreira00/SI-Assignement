
CREATE OR REPLACE PROCEDURE MakeReservation(
    IN client_id INTEGER,
    IN lojaId INTEGER,
    IN bicicletaId INTEGER,
    IN dtInicio_p TIMESTAMP,
    IN dtFim_p TIMESTAMP,
    IN valor_p NUMERIC(5,2),
    IN version_p INTEGER
)
    LANGUAGE plpgsql
AS $$
DECLARE
    noReserva_p INTEGER;
    atrdisc_p CHAR(1);
    non_reserved_bikes INTEGER;
    reserved_bikes INTEGER;
    min_reserved_bikes INTEGER;
BEGIN

    IF NOT EXISTS (SELECT 1 FROM LOJA WHERE codigo = lojaId) THEN
        RAISE EXCEPTION 'Invalid store ID';
    END IF;

    IF NOT EXISTS (SELECT 1 FROM BICICLETA WHERE id_bicicleta = bicicletaId) THEN
        RAISE EXCEPTION 'Invalid bicycle ID';
    END IF;

    IF EXISTS(SELECT 1 FROM BICICLETA WHERE id_bicicleta = bicicletaId AND estado != 'livre') THEN
        RAISE EXCEPTION 'Bicycle is not available';
    END IF;

    SELECT COUNT(*) INTO reserved_bikes FROM BICICLETA WHERE estado = 'ocupado';
    SELECT COUNT(*) INTO non_reserved_bikes FROM BICICLETA WHERE estado = 'livre';

 --   min_reserved_bikes := non_reserved_bikes * 0.1;

   -- IF reserved_bikes <= min_reserved_bikes THEN
   --    RAISE EXCEPTION 'Not enough bikes reserved';
  --  END IF;

    IF dtInicio_p >= dtFim_p THEN
        RAISE EXCEPTION 'Invalid date range';
    END IF;



    SELECT COALESCE(MAX(noreserva), 0) + 1 INTO noReserva_p FROM RESERVA;

    INSERT INTO RESERVA (noreserva,loja, dtinicio, dtfim, valor, bicicleta, version)
    VALUES (noReserva_p,lojaId, dtInicio_p, dtFim_p, valor_p, bicicletaId, version_p);

    INSERT INTO clientereserva (cliente, reserva, loja, version) VALUES
    (client_id, noReserva_p, lojaId, version_p);

    UPDATE BICICLETA SET estado = 'ocupado' WHERE id_bicicleta = bicicletaId;



END
$$;

 call MakeReservation(1, 1, 3, '2021-01-01 00:00:00', '2021-01-02 00:00:00', 10.00);

