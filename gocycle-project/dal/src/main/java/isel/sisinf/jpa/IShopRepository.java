package isel.sisinf.jpa;

import isel.sisinf.model.Shop;
import isel.sisinf.model.genericInterfaces.IShop;
import isel.sisinf.jpa.genericInterfaces.IRepository;

import java.util.Collection;


public interface IShopRepository extends IRepository<IShop,Collection<IShop>,Integer>,IShopDataMapper{

    public Shop findShopByKey(Integer key);

}
