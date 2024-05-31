

CREATE OR REPLACE FUNCTION verifySingleBycicleState(
     date timestamp,
    id INTEGER
) RETURNS BOOLEAN AS $$
    BEGIN
        IF EXISTS (SELECT * FROM reserva Where date BETWEEN dtinicio AND dtfim AND bicicleta = id)
        THEN
            RETURN FALSE;
        ELSE
            RETURN TRUE;
        END IF;
    END;
$$ LANGUAGE plpgsql;