package isel.sisinf.jpa;

import isel.sisinf.jpa.genericInterfaces.IDataMapper;
import isel.sisinf.jpa.genericInterfaces.IRepository;
import isel.sisinf.model.IBycicle;

import java.util.Collection;


public interface IBycicleRepository extends IRepository<IBycicle, Collection<IBycicle>, Integer>, IDataMapper<IBycicle> {
}
