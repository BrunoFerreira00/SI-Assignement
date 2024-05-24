package isel.sisinf.jpa;

import isel.sisinf.model.genericInterfaces.IClient;

public interface IContext extends AutoCloseable {

    enum IsolationLevel {READ_UNCOMMITTED, READ_COMMITTED, REPEATABLE_READ, SERIALIZABLE };
    void beginTransaction();
    void beginTransaction(IsolationLevel isolationLevel);
    void commit();
    void flush();
    void clear();
    void persist(Object entity);

    IShopRepository getShops();
    IBycicleRepository getBycicles();
    IEletricBicycleRepository getEletricBicycles();
    IGPSRepository getGPSs();
    IClientRepository getClients();





}