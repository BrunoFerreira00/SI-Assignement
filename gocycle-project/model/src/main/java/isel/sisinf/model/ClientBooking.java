package isel.sisinf.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "clientereserva")
public class ClientBooking {
    private Integer cliente;
    @Id
    private Integer reserva;

    private Integer loja;

    public Integer getClientCode() {
        return cliente;
    }

    public void setClientCode(Integer cliente) {
        this.cliente = cliente;
    }

    public Integer getBookingCode() {
        return reserva;
    }

    public void setBookingCode(Integer reserva) {
        this.reserva = reserva;
    }

    public Integer getShopCode() {
        return loja;
    }

    public void setShopCode(Integer loja) {
        this.loja = loja;
    }
}
