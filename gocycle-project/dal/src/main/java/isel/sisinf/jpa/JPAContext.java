package isel.sisinf.jpa;

import isel.sisinf.model.*;
import isel.sisinf.model.genericInterfaces.IShop;
import jakarta.persistence.*;
import org.eclipse.persistence.sessions.DatabaseLogin;
import org.eclipse.persistence.sessions.Session;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

public class JPAContext implements IContext  {

    private EntityManagerFactory _emf;
    private EntityManager _em;

    private EntityTransaction _tx;
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
    public void rollback() {
        if(_tx != null)
            _tx.rollback();
        _tx = null;
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
    public IShopRepository getShops() {
        return _shopRepository;
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
        public Bicycle create(Bicycle entity) {
            return (Bicycle) helperCreateImpl(entity);
        }

        @Override
        public Bicycle update(Bicycle entity) {
            return null;
        }

        @Override
        public Bicycle delete(Bicycle entity) {
            return null;
        }

        @Override
        public Bicycle findByKey(Integer key) {
            return _em.find(Bicycle.class, key);
        }

        @Override
        public Collection<Bicycle> find(String jpql, Object... params) {
            return (Collection<Bicycle>) helperQueryImpl(jpql, params);
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
        public void createReservation(Reservation entity, Integer client_id) {
            try {


                _em.getTransaction().begin();
                Query query = _em.createNativeQuery("call MakeReservation(?1,?2,?3,?4,?5,?6, ?7)");

                query.setParameter(1,client_id);
                query.setParameter(2, entity.getLoja().getCodigo());
                query.setParameter(3, entity.getNoreserva());
                query.setParameter(4, entity.getDtinicio());
                query.setParameter(5, entity.getDtfim());
                query.setParameter(6, entity.getValor());
                query.setParameter(7, 0);

                // Execute the stored procedure
                query.executeUpdate();
                _em.getTransaction().commit();
            } catch (Exception e) {
                // Log the exception (consider using a logging framework)
                System.err.println("Error during reservation creation: " + e.getMessage());
                e.printStackTrace();
                throw new RuntimeException("Reservation creation failed", e);
            }
        }


        @Override
        public Reservation create(Reservation entity) {
            return null;
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
        public Reservation deleteOptmisticLocking(Reservation entity) {
            try{
                _em.lock(entity,LockModeType.OPTIMISTIC);
                return (Reservation) helperDeleteteImpl(entity);
            } catch (OptimisticLockException e) {
                System.out.println("Optimistic lock exception occurred. Another transaction may have updated the entity.");
                return null;
            }
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
            return (ClientBooking) helperDeleteteImpl(entity);
        }
        @Override
        public ClientBooking deleteOptmisticLocking(ClientBooking entity) {
            try{
                _em.lock(entity,LockModeType.OPTIMISTIC);
                return (ClientBooking) helperDeleteteImpl(entity);
            } catch (OptimisticLockException e) {
                System.out.println("Optimistic lock exception occurred. Another transaction may have updated the entity.");
                return null;
            }
        }

        @Override
        public ClientBooking findByEmbeddedKey(ClientReservationId key) {


            String queryString = "SELECT c FROM clientereserva c WHERE c.id.cliente = :cliente AND c.id.reserva = :reserva AND c.id.loja = :loja";
            TypedQuery<ClientBooking> query = _em.createQuery(queryString, ClientBooking.class);
            query.setParameter("cliente", key.getCliente());
            query.setParameter("reserva", key.getReserva());
            query.setParameter("loja", key.getLoja());
            ClientBooking booking = query.getSingleResult();
            return booking;

        }

        @Override
        public ClientBooking findByKey(Integer key) {
            return null;
        }

        @Override
        public Collection<ClientBooking> find(String jpql, Object... params) {
            return (Collection<ClientBooking>) helperQueryImpl(jpql, params);
        }
        
    }
    
    protected class shopRepository implements IShopRepository {
        @Override
        public Shop create(Shop entity) {
            return (Shop) helperCreateImpl(entity);
        }

        @Override
        public Shop update(Shop entity) {
            return null;
        }

        @Override
        public Shop delete(Shop entity) {
            return null;
        }

        @Override
        public Shop findShopByKey(Integer key) {
            return _em.find(Shop.class, key);
        }


        @Override
        public IShop findByKey(Integer key) {
            return null;
        }

        @Override
        public Collection<IShop> find(String jpql, Object... params) {
            return null;
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
        this._shopRepository = new shopRepository();

    }





}
