

/*
public class BicicletaService {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gocyclePU");

    public List<Bycicle> listarBicicletas() {
        try {
            TypedQuery<Bycicle> query = em.createQuery("SELECT b FROM bicicleta b", Bycicle.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<EletricBicycle> listarBicicletasEletricas() {
        try {
            TypedQuery<EletricBicycle> query = em.createQuery("SELECT e FROM eletrica e", EletricBicycle.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public boolean verificarDisponibilidade(Integer bicicletaId, LocalDateTime momento) {
        try {
            // Convert LocalDateTime to Timestamp
            Timestamp momentoTimestamp = Timestamp.valueOf(momento);

            TypedQuery<Reservation> query = em.createQuery(
                    "SELECT r FROM reserva r WHERE r.bicicleta.id_bicicleta = :bicicletaId " +
                            "AND r.dtinicio <= :momento AND r.dtfim >= :momento", Reservation.class);
            query.setParameter("bicicletaId", bicicletaId);
            query.setParameter("momento", momentoTimestamp);

            List<Reservation> reservas = query.getResultList();
            return reservas.isEmpty();
        } finally {
            em.close();
        }
    }
}*/
