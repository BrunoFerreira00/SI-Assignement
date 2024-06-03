package isel.sisinf.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity(name = "reserva")
public class Reservation {
    @Id
    private Integer noreserva;


    private Integer loja; // OneToOne with Shop
    private Timestamp dtinicio;
    private Timestamp dtfim;
    @Column(name = "valor", precision = 5, scale = 2)
    private BigDecimal valor;
    private Integer bicicleta; // OneToOne with Bycicle

    public void Reservation() {}

    public void Reservation(Integer noreserva, Integer loja, Timestamp dtinicio, Timestamp dtfim, BigDecimal valor, Integer bicicleta) {
        this.noreserva = noreserva;
        this.loja = loja;
        this.dtinicio = dtinicio;
        this.dtfim = dtfim;
        this.valor = valor;
        this.bicicleta = bicicleta;
    }

    public Integer getId() {
        return noreserva;
    }
    public Integer getShop(){return loja;}

    public Timestamp getInitialDate() {
        return dtinicio;
    }

    public Timestamp getFinalDate() {
        return dtfim;
    }

    public Integer getBicycleCode() {
        return bicicleta;
    }

    public BigDecimal getPrice() {return valor;}

    public void setId(Integer noreserva){this.noreserva = noreserva;}

    public void setShop(Integer loja){this.loja = loja;}

    public void setInitialDate(Timestamp dtinicio){
        this.dtinicio = dtinicio;
    }

    public void setFinalDate(Timestamp dtfim){
        this.dtfim = dtfim;
    }
    public void setBicycleCode(Integer bicicleta){
        this.bicicleta = bicicleta;
    }

    public void setPrice(BigDecimal valor){
        this.valor = valor;
    }

}
