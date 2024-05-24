package isel.sisinf.jpa;

import isel.sisinf.model.genericInterfaces.IGPS;
import isel.sisinf.jpa.genericInterfaces.IRepository;

import java.util.Collection;

public interface IGPSRepository extends IRepository<IGPS, Collection<IGPS>,Integer>,IGPSDataMapper{
}
