package isel.sisinf.jpa;

import isel.sisinf.jpa.genericInterfaces.IRepository;
import isel.sisinf.model.Client;
import isel.sisinf.model.ClientBooking;
import isel.sisinf.model.ClientReservationId;

import java.util.Collection;

public interface IClientBookingRepository extends IRepository<ClientBooking, Collection<ClientBooking>,Integer>,IClientBookingMapper{

    ClientBooking deleteOptmisticLocking(ClientBooking entity);

    ClientBooking findByEmbeddedKey(ClientReservationId key);
}
