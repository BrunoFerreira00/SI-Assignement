package isel.sisinf.jpa;

import isel.sisinf.jpa.genericInterfaces.IRepository;
import isel.sisinf.model.Bycicle;

import java.util.Collection;
import java.util.List;


public interface IBycicleRepository extends IRepository<Bycicle, Collection<Bycicle>, Integer>, IBycicleDataMapper{

    List<Bycicle> checkAvailability(); // talvez string date;
}
