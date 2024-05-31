package isel.sisinf.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "bicicleta")
public class Bycicle implements IBycicle {
    private String atrdisc;
    @Id
    private Integer id_bicicleta;
    private Integer peso;
    private String modelo;
    private String marca;
    private Integer mudanca; // Can only be {1, 6, 18, 24}
    private String estado; // Can only be {livre, ocupado, reserva, em manutenção}
    private String gps;

    /*
    public Bicycle(String atrdisc, Integer id_bicicleta, Integer peso, String modelo, String marca, Integer mudanca, String state, String gps) {
        this.atrdisc = atrdisc;
        this.id_bicicleta = id_bicicleta;
        this.peso = peso;
        this.modelo = modelo;
        this.marca = marca;
        this.mudanca = mudanca;
        this.state = state;
        this.gps = gps;
    }
     */
    public String getAtrdisc() {
        return atrdisc;
    }

    public Integer getId_bicicleta() {
        return id_bicicleta;
    }

    public Integer getPeso() {
        return peso;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

    public Integer getMudanca() {
        return mudanca;
    }

    public String getEstado() {
        return estado;
    }

    public String getGps() {
        return gps;
    }

    public void setAtrdisc(String atrdisc) {
        this.atrdisc = atrdisc;
    }
    public void setId_bicicleta(Integer id_bicicleta) {
        this.id_bicicleta = id_bicicleta;
    }
    public void setPeso(Integer peso) {
        this.peso = peso;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public void setMudanca(Integer mudanca) {
        this.mudanca = mudanca;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public void setGps(String gps) {
        this.gps = gps;
    }
}
