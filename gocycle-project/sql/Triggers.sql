CREATE OR REPLACE FUNCTION check_reservation_date() RETURNS TRIGGER AS $$
    BEGIN
        IF NEW.dtinicio >= NEW.dtfim THEN
            RAISE EXCEPTION 'Data de início da reserva deve ser anterior à data de fim';
        END IF;
        RETURN NEW;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_reservation_date
    BEFORE INSERT OR UPDATE
    ON RESERVA
    FOR EACH ROW
    EXECUTE FUNCTION check_reservation_date();


CREATE OR REPLACE FUNCTION check_bicycle_state() RETURNS TRIGGER AS $$
    DECLARE
        state VARCHAR(30);
    BEGIN
        SELECT estado INTO state FROM BICICLETA WHERE id_bicicleta = NEW.bicicleta;
        IF state != 'livre' THEN
            RAISE EXCEPTION 'Bicicleta não está disponível';
        END IF;
        RETURN NEW;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_bicycle_state
    BEFORE INSERT OR UPDATE
    ON RESERVA
    FOR EACH ROW
    EXECUTE FUNCTION check_bicycle_state();

