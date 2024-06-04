package isel.sisinf.model;

import jakarta.persistence.*;

@Entity(name = "clientereserva")
public class ClientBooking {

    @EmbeddedId
    private ClientReservationId id;

    @ManyToOne
    @MapsId("cliente")
    @JoinColumn(name = "cliente", nullable = false)
    private Client cliente;

    @ManyToOne
    @MapsId("reserva")
    @JoinColumns({
            @JoinColumn(name = "reserva", referencedColumnName = "noreserva"),
            @JoinColumn(name = "loja", referencedColumnName = "loja")
    })
    private Reservation reserva;


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
}