package isel.sisinf.jpa;

import isel.sisinf.model.*;
import isel.sisinf.model.genericInterfaces.IClientBooking;
import isel.sisinf.model.genericInterfaces.IReservation;
import jakarta.persistence.*;
import org.eclipse.persistence.sessions.DatabaseLogin;
import org.eclipse.persistence.sessions.Session;
import org.postgresql.core.NativeQuery;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
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
    private IElectricBicycleRepository _electricBicycleRepository;
    private IClientRepository _clientRepository;
    private IReservationRepository _reservationRepository;


    private IClientBookingRepository _clientBookingRepository;
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
    public IClientRepository getClients() {
        return _clientRepository;
    }

    @Override
    public IBycicleRepository getBycicles() {
        return _bycicleRepository;
    }

    @Override
    public IReservationRepository getBookings() {
        return _reservationRepository;
    }

    @Override
    public IClientBookingRepository getClientBookings() {
        return _clientBookingRepository;
    }

    @Override
    public void close() throws Exception {

        if(_tx != null)
            _tx.rollback();
        _em.close();
        _emf.close();
    }

    protected class ClientRepository implements IClientRepository {

        @Override
        public Client create(Client entity) {
            return (Client) helperCreateImpl(entity);
        }

        @Override
        public Client update(Client entity) {
            return null;
        }

        @Override
        public Client delete(Client entity) {
            return null;
        }

        @Override
        public Client findByKey(Integer key) {
            return _em.find(Client.class, key);
        }

        @Override
        public Collection<Client> find(String jpql, Object... params) {
            return (Collection<Client>) helperQueryImpl(jpql, params);
        }

        @Override
        public Client findClientWithBiggestId() {
            String jpql = "SELECT c FROM pessoa c ORDER BY c.id DESC";
            List<Client> clients = (List<Client>) find(jpql);
            return clients.isEmpty() ? null : clients.get(0);
        }
    }

    protected class BycicleRepository implements IBycicleRepository {

        @Override
        public Bycicle create(Bycicle entity) {
            return (Bycicle) helperCreateImpl(entity);
        }

        @Override
        public Bycicle update(Bycicle entity) {
            return null;
        }

        @Override
        public Bycicle delete(Bycicle entity) {
            return null;
        }

        @Override
        public Bycicle findByKey(Integer key) {
            return _em.find(Bycicle.class, key);
        }

        @Override
        public Collection<Bycicle> find(String jpql, Object... params) {
            return (Collection<Bycicle>) helperQueryImpl(jpql, params);
        }

        @Override
        public Boolean checkAvailability(Timestamp date, Integer id) {
            try {
                _em.getTransaction().begin();
                Query q = _em.createNativeQuery("SELECT * FROM verifysinglebyciclestate(?, ?)");
                q.setParameter(1, date);
                q.setParameter(2, id);
                return(Boolean) q.getSingleResult();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                _em.getTransaction().commit();
            }


        }
    }

    protected class ReservationRepository implements IReservationRepository{
        @Override
        public Reservation create(Reservation entity) {
            try {


                _em.getTransaction().begin();
                Query query = _em.createNativeQuery("call MakeReservation(?1,?2,?3,?4,?5)");
                // Register the parameters with their positions and types
                /*query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
                query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
                query.registerStoredProcedureParameter(3, Timestamp.class, ParameterMode.IN);
                query.registerStoredProcedureParameter(4, Timestamp.class, ParameterMode.IN);
                query.registerStoredProcedureParameter(5, Double.class, ParameterMode.IN);*/

                // Set the parameter values by position
                query.setParameter(1, entity.getShop());
                query.setParameter(2, entity.getBicycleCode());
                query.setParameter(3, entity.getInitialDate());
                query.setParameter(4, entity.getFinalDate());
                query.setParameter(5, entity.getPrice());

                // Execute the stored procedure
                query.executeUpdate();
                _em.getTransaction().commit();
                return entity;
            } catch (Exception e) {
                // Log the exception (consider using a logging framework)
                System.err.println("Error during reservation creation: " + e.getMessage());
                e.printStackTrace();
                throw new RuntimeException("Reservation creation failed", e);
            }
        }


        @Override
        public Reservation update(Reservation entity) {
            return null;
        }

        @Override
        public Reservation delete(Reservation entity) {
            return (Reservation) helperDeleteteImpl(entity);
        }

        @Override
        public Reservation findByKey(Integer key) {
            return _em.find(Reservation.class, key);
        }

        @Override
        public Collection<Reservation> find(String jpql, Object... params) {
            return (Collection<Reservation>) helperQueryImpl(jpql,params);
        }
        @Override
        public Reservation findReservationWithBiggestId() {
            String jpql = "SELECT r FROM reserva r ORDER BY r.noreserva DESC";
            List<Reservation> reservations = (List<Reservation>) find(jpql);
            return reservations.isEmpty() ? null : reservations.get(0);
        }


    }

    protected class ClientReservationRepository implements IClientBookingRepository {
        @Override
        public ClientBooking create(ClientBooking entity) {
            return (ClientBooking) helperCreateImpl(entity);
        }

        @Override
        public ClientBooking update(ClientBooking entity) {
            return null;
        }

        @Override
        public ClientBooking delete(ClientBooking entity) {
            return null;
        }

        @Override
        public ClientBooking findByKey(Integer key) {
            return _em.find(ClientBooking.class, key);
        }

        @Override
        public Collection<ClientBooking> find(String jpql, Object... params) {
            return (Collection<ClientBooking>) helperQueryImpl(jpql, params);
        }
    }

    public JPAContext() {
        this("SI-Project");
    }

    public JPAContext(String persistentCtx)
    {
        super();

        this._emf = Persistence.createEntityManagerFactory(persistentCtx);
        this._em = _emf.createEntityManager();
        this._clientRepository = new ClientRepository();
        this._bycicleRepository = new BycicleRepository();
        this._reservationRepository = new ReservationRepository();
        this._clientBookingRepository = new ClientReservationRepository();

    }





}
