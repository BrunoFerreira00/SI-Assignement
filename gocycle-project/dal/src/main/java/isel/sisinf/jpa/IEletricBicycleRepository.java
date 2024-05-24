package isel.sisinf.jpa;

import isel.sisinf.jpa.genericInterfaces.IDataMapper;
import isel.sisinf.jpa.genericInterfaces.IRepository;
import isel.sisinf.model.IEletricBicycle;

import java.util.Collection;

public interface IEletricBicycleRepository extends IRepository<IEletricBicycle, Collection<IEletricBicycle>, Integer>, IDataMapper<IEletricBicycle> {
}
