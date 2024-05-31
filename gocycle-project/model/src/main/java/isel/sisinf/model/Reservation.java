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
    private Integer loja;
    private Timestamp dtinicio;
    private Timestamp dtfim;
    @Column(name = "valor", precision = 5, scale = 2)
    private BigDecimal valor;
    private Integer bicicleta;

/*
    public Reservation(Integer code, String initialDate, String finalDate, Integer bicycleCode, String price) {
        this.code = code;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.bicycleCode = bicycleCode;
        this.price = price;
    }

 */

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
