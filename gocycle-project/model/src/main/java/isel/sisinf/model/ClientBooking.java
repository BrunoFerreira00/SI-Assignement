package isel.sisinf.model;

import jakarta.persistence.*;

@Entity(name = "clientereserva")
public class ClientBooking {

    @EmbeddedId
    private ClientReservationId id;

    @ManyToOne
    @JoinColumn(name = "cliente", insertable = false, updatable = false)
    private Client client;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "reserva", insertable = false, updatable = false),
            @JoinColumn(name = "loja", insertable = false, updatable = false)
    })
    private Reservation reservation;

    public void ClientBooking() {}

    public void ClientBooking(ClientReservationId id, Client client, Reservation reservation) {
        this.id = id;
        this.client = client;
        this.reservation = reservation;
    }

    public ClientReservationId getId() {
        return id;
    }

    public void setId(ClientReservationId id) {
        this.id = id;
    }

    public Integer getClientCode() {
        return id.getCliente();
    }

    public void setClientCode(Integer cliente) {
        id.setCliente(cliente);
    }

    public Integer getBookingCode() {
        return id.getReserva();
    }

    public void setBookingCode(Integer reserva) {
        id.setReserva(reserva);
    }

    public Integer getShopCode() {
        return id.getLoja();
    }

    public void setShopCode(Integer loja) {
        id.setLoja(loja);
    }

    // Getters and setters for the associated entities
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}