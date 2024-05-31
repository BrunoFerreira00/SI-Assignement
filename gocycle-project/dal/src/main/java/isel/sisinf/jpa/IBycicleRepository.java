package isel.sisinf.jpa;

import isel.sisinf.jpa.genericInterfaces.IRepository;
import isel.sisinf.model.Bycicle;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;


public interface IBycicleRepository extends IRepository<Bycicle, Collection<Bycicle>, Integer>, IBycicleDataMapper{

    Boolean checkAvailability(Timestamp date,Integer id); // talvez string date;
}
