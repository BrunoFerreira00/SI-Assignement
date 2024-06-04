package isel.sisinf.jpa;

import isel.sisinf.jpa.genericInterfaces.IRepository;
import isel.sisinf.model.Bicycle;

import java.sql.Timestamp;
import java.util.Collection;


public interface IBycicleRepository extends IRepository<Bicycle, Collection<Bicycle>, Integer>, IBycicleDataMapper{

    Boolean checkAvailability(Timestamp date,Integer id); // talvez string date;
}
