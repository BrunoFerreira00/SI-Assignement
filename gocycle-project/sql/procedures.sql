
CREATE OR REPLACE PROCEDURE MakeReservation(
    --IN clienteId INTEGER,
    IN lojaId INTEGER,
    IN bicicletaId INTEGER,
    IN dtInicio_p TIMESTAMP,
    IN dtFim_p TIMESTAMP,
    IN valor_p NUMERIC(5,2)
)
    LANGUAGE plpgsql
AS $$
DECLARE
    noReserva_p INTEGER;
BEGIN

    IF NOT EXISTS (SELECT 1 FROM LOJA WHERE codigo = lojaId) THEN
        RAISE EXCEPTION 'Invalid store ID';
    END IF;

    IF NOT EXISTS (SELECT 1 FROM BICICLETA WHERE id_bicicleta = bicicletaId) THEN
        RAISE EXCEPTION 'Invalid bicycle ID';
    END IF;

    IF dtInicio_p >= dtFim_p THEN
        RAISE EXCEPTION 'Invalid date range';
    END IF;

    SELECT COALESCE(MAX(noreserva), 0) + 1 INTO noReserva_p FROM RESERVA;

    INSERT INTO RESERVA (noreserva,loja, dtinicio, dtfim, valor, bicicleta)
    VALUES (noReserva_p,lojaId, dtInicio_p, dtFim_p, valor_p, bicicletaId);


    --INSERT INTO CLIENTERESERVA (cliente, reserva, loja)
    --VALUES (clienteId, noReserva_p, lojaId);


END
$$;

 call MakeReservation(1, 1, 3, '2021-01-01 00:00:00', '2021-01-02 00:00:00', 10.00);

