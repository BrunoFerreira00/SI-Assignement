package isel.sisinf.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ClientReservationId implements Serializable {
    private Integer cliente;
    private Integer reserva;
    @Column(name = "loja", insertable = false, updatable = false)
    private Integer loja;

    // Default constructor
    public void ClientReservationId() {}

    // Parameterized constructor
    public void ClientReservationId(Integer cliente, Integer reserva, Integer loja) {
        this.cliente = cliente;
        this.reserva = reserva;
        this.loja = loja;
    }

    // Getters and setters
    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public Integer getReserva() {
        return reserva;
    }

    public void setReserva(Integer reserva) {
        this.reserva = reserva;
    }

    public Integer getLoja() {
        return loja;
    }

    public void setLoja(Integer loja) {
        this.loja = loja;
    }

    // hashCode and equals methods
    @Override
    public int hashCode() {
        // Custom hashCode implementation
        return Objects.hash(cliente, reserva, loja);
    }

    @Override
    public boolean equals(Object obj) {
        // Custom equals implementation
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ClientReservationId that = (ClientReservationId) obj;
        return Objects.equals(cliente, that.cliente) &&
                Objects.equals(reserva, that.reserva) &&
                Objects.equals(loja, that.loja);
    }
}
