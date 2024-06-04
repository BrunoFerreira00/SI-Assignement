/*
package isel.sisinf.service;

import isel.sisinf.model.Bycicle;
import isel.sisinf.model.Reservation;
import isel.sisinf.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.TypedQuery;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class BicicletaService {
    EntityManager em = JPAUtil.getEntityManager();
    public List<Bycicle> listarBicicletas() {
        try {
            TypedQuery<Bycicle> query = em.createQuery("SELECT b FROM bicicleta b", Bycicle.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    public boolean verificarDisponibilidade(Integer bicicletaId, LocalDateTime momento) {
        try {
            Timestamp momentoTimestamp = Timestamp.valueOf(momento);

            TypedQuery<Reservation> query = em.createQuery(
                    "SELECT r FROM reserva r WHERE r.bicicleta = :bicicletaId " +
                            "AND r.dtinicio <= :momento AND r.dtfim >= :momento", Reservation.class);
            query.setParameter("bicicletaId", bicicletaId);
            query.setParameter("momento", momentoTimestamp);

            List<Reservation> reservas = query.getResultList();
            return reservas.isEmpty();
        } finally {
            em.close();
        }
    }
    public void updateBycicle(Integer bicicletaId, String novoEstado) {
        try {
            em.getTransaction().begin();
            Bycicle bicicleta = em.find(Bycicle.class, bicicletaId);
            if (bicicleta != null) {
                // Verifica o estado atual da bicicleta
                String estadoAtual = bicicleta.getEstado();
                if (!estadoAtual.equals("ocupado")) {
                    bicicleta.setEstado(novoEstado);
                    em.merge(bicicleta);
                    em.getTransaction().commit();
                }
            } else {
                throw new IllegalArgumentException("Bicicleta não encontrada.");
            }
        } finally {
            em.close();
        }
    }
}
*/