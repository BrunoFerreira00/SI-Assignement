package isel.sisinf.jpa;

import isel.sisinf.jpa.genericInterfaces.IDataMapper;
import isel.sisinf.jpa.genericInterfaces.IRepository;
import isel.sisinf.model.Reservation;
import isel.sisinf.model.genericInterfaces.IReservation;

import java.util.Collection;

public interface IReservationRepository extends IRepository<Reservation, Collection<Reservation>,Integer>, IReservationDataMapper {
    Reservation findReservationWithBiggestId();
}
