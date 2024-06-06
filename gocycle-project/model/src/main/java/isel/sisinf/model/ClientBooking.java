package isel.sisinf.model;

import jakarta.persistence.*;

@Entity(name = "clientereserva")
public class ClientBooking {

    @EmbeddedId
    private ClientReservationId id;

    @ManyToOne
    @JoinColumn(name = "cliente", nullable = false)
    private Client cliente;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "reserva", referencedColumnName = "noreserva"),
            @JoinColumn(name = "loja", referencedColumnName = "loja")
    })
    private Reservation reserva;

    @Version
    @Column(name = "version")
    private Integer version = 0;


    public ClientReservationId getId() {
        return id;
    }

    public void setId(ClientReservationId id) {
        this.id = id;
    }

    public Client getCliente() {
        return cliente;
    }

    public void setCliente(Client cliente) {
        this.cliente = cliente;
    }

    public Reservation getReserva() {
        return reserva;
    }

    public void setReserva(Reservation reserva) {
        this.reserva = reserva;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}