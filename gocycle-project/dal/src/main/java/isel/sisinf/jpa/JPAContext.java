package isel.sisinf.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.eclipse.persistence.sessions.DatabaseLogin;
import org.eclipse.persistence.sessions.Session;

import java.util.List;

public class JPAContext implements IContext  {

    private EntityManagerFactory _emf;
    private EntityManager _em;

    //Simple implementation for flat transaction support
    private EntityTransaction _tx;
    //b) What is the purpose of _txCount?
    private int _txcount;
    private IShopRepository _shopRepository;
    private IGPSRepository _gpsRepository;
    private IBycicleRepository _bycicleRepository;
    private IEletricBicycleRepository _eletricBicycleRepository;
    private IClientRepository _clientRepository;
    private IReservationRepository _reservationRepository;
    protected List helperQueryImpl(String jpql, Object... params)
    {
        Query q = _em.createQuery(jpql);

        for(int i = 0; i < params.length; ++i)
            q.setParameter(i+1, params[i]);

        return q.getResultList();
    }

    protected Object helperCreateImpl(Object entity)
    {
        beginTransaction(); //Each write can have multiple inserts on the DB. See the relations mapping.
        _em.persist(entity);
        commit(); //c) Why can we commit a transaction here, if other commands can be sent to the database?
        return entity;
    }

    protected Object helperUpdateImpl(Object entity)
    {
        beginTransaction(); //Each write can have multiple inserts on the DB. See the relations mapping.
        _em.merge(entity); //d) What does merge do?
        commit();
        return entity;
    }

    protected Object helperDeleteteImpl(Object entity)
    {
        beginTransaction(); //Each write can have multiple inserts on the DB. See the relations.
        _em.remove(entity);
        commit();
        return entity;
    }
    @Override
    public void beginTransaction() {
        if(_tx == null)
        {
            _tx = _em.getTransaction();
            _tx.begin();
            _txcount=0;
        }
        ++_txcount;
    }

    @Override
    public void beginTransaction(IsolationLevel isolationLevel)
    {
        beginTransaction();
        Session session =_em.unwrap(Session.class);
        DatabaseLogin databaseLogin = (DatabaseLogin) session.getDatasourceLogin();
        System.out.println(databaseLogin.getTransactionIsolation());

        int isolation = DatabaseLogin.TRANSACTION_READ_COMMITTED;
        if(isolationLevel == IsolationLevel.READ_UNCOMMITTED)
            isolation = DatabaseLogin.TRANSACTION_READ_UNCOMMITTED;
        else if(isolationLevel == IsolationLevel.REPEATABLE_READ)
            isolation = DatabaseLogin.TRANSACTION_REPEATABLE_READ;
        else if(isolationLevel == IsolationLevel.SERIALIZABLE)
            isolation = DatabaseLogin.TRANSACTION_SERIALIZABLE;

        databaseLogin.setTransactionIsolation(isolation);
    }

    @Override
    public void commit() {

        --_txcount;
        if(_txcount==0 && _tx != null)
        {
            _em.flush(); //To assure all changes in memory go into the database
            _tx.commit();
            _tx = null;
        }
    }

    @Override
    public void flush() {
        _em.flush();
    }


    @Override
    public void clear() {
        _em.clear();

    }

    @Override
    public void persist(Object entity) {
        _em.persist(entity);

    }

    @Override
    public void close() throws Exception {

        if(_tx != null)
            _tx.rollback();
        _em.close();
        _emf.close();
    }




}
