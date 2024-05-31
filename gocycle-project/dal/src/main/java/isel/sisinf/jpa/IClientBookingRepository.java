package isel.sisinf.jpa;

import isel.sisinf.jpa.genericInterfaces.IRepository;
import isel.sisinf.model.Client;
import isel.sisinf.model.ClientBooking;

import java.util.Collection;

public interface IClientBookingRepository extends IRepository<ClientBooking, Collection<ClientBooking>,Integer>,IClientBookingMapper{
}
