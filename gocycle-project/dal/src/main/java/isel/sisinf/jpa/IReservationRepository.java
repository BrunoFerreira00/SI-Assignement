package isel.sisinf.jpa;

import isel.sisinf.jpa.genericInterfaces.IRepository;
import isel.sisinf.model.ClientBooking;
import isel.sisinf.model.Reservation;

import java.util.Collection;

public interface IReservationRepository extends IRepository<Reservation, Collection<Reservation>,Integer>, IReservationDataMapper {

    Reservation deleteOptmisticLocking(Reservation entity);

    Reservation findReservationWithBiggestId();

    void createReservation(Reservation entity, Integer client_id);
}
