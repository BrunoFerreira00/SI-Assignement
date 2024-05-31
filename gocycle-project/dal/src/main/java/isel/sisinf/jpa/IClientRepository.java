package isel.sisinf.jpa;

import isel.sisinf.model.Client;
import isel.sisinf.model.genericInterfaces.IClient;
import isel.sisinf.jpa.genericInterfaces.IRepository;

import java.util.Collection;

public interface IClientRepository extends IRepository<Client, Collection<Client>,Integer>,IClientDataMapper{

    Client findClientWithBiggestId();
}
