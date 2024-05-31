package isel.sisinf.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "reserva")
public class Reservation {
    @Id
    private Integer noreserva;
    private Integer loja;
    private String dtinicio;
    private String dtfim;
    private String valor;
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

    public String getInitialDate() {
        return dtinicio;
    }

    public String getFinalDate() {
        return dtfim;
    }

    public Integer getBicycleCode() {
        return bicicleta;
    }

    public String getPrice() {return valor;}

    public void setId(Integer noreserva){this.noreserva = noreserva;}

    public void setShop(Integer loja){this.loja = loja;}

    public void setInitialDate(String dtinicio){
        this.dtinicio = dtinicio;
    }

    public void setFinalDate(String dtfim){
        this.dtfim = dtfim;
    }
    public void setBicycleCode(Integer bicicleta){
        this.bicicleta = bicicleta;
    }

    public void setPrice(String valor){
        this.valor = valor;
    }

}
