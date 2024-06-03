package isel.sisinf.model;

import isel.sisinf.model.genericInterfaces.IEletricBicycle;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "eletrica")
public class EletricBicycle implements IEletricBicycle {
    private String type;
    @Id // should be OneToOne with Bycicle
    private Integer code;
    private Integer weight;
    private String model;
    private String brand;
    private Integer changeSystem; // Can only be {1, 6, 18, 24}
    private String state; // Can only be {livre, ocupado, reserva, em manutenção}
    private Integer GPS;
    private Integer autonomy;
    private Integer maxSpeed;

    public void EletricBicycle() {}

    public void EletricBicycle(String type, Integer code, Integer weight, String model, String brand, Integer changeSystem, String state, String GPS, Integer autonomy, Integer maxSpeed) {
        this.type = type;
        this.code = code;
        this.weight = weight;
        this.model = model;
        this.brand = brand;
        this.changeSystem = changeSystem;
        this.state = state;
        this.GPS = GPS;
        this.autonomy = autonomy;
        this.maxSpeed = maxSpeed;
    }




    public Integer getAutonomy() {
        return autonomy;
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    public String getAtrdisc() {
        return type;
    }

    public Integer getId_bicicleta() {
        return code;
    }

    public Integer getPeso() {
        return weight;
    }

    public String getModelo() {
        return model;
    }

    public String getMarca() {
        return brand;
    }

    public Integer getMudanca() {
        return changeSystem;
    }

    public String getEstado() {
        return state;
    }

    public Integer getGps() {
        return GPS;
    }

    public void setAtrdisc(String atrdisc) {
        this.type = atrdisc;
    }
    public void setId_bicicleta(Integer id_bicicleta) {
        this.code = id_bicicleta;
    }
    public void setPeso(Integer peso) {
        this.weight = peso;
    }
    public void setModelo(String modelo) {
        this.model = modelo;
    }
    public void setMarca(String marca) {
        this.brand = marca;
    }
    public void setMudanca(Integer mudanca) {
        this.changeSystem = mudanca;
    }
    public void setEstado(String estado) {
        this.state = estado;
    }
    public void setGps(Integer gps) {
        this.GPS = gps;
    }
    public void setAutonomy(Integer autonomy){
        this.autonomy = autonomy;
    }
    public void setMaxSpeed(Integer speed){
        this.maxSpeed = speed;
    }
}
