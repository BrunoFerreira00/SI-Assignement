package isel.sisinf.model;

import isel.sisinf.model.genericInterfaces.IBycicle;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "bicicleta")
public class Bicycle  { // implements IBycicle
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_bicicleta;

    @Column(nullable = false, precision = 4, scale = 2)
    private Double peso;

    @Column(nullable = false)
    private Integer raio;

    @Column(nullable = false, length = 20)
    private String modelo;

    @Column(nullable = false, length = 30)
    private String marca;

    @Column (nullable = false)
    private Integer mudanca;

    @Column(nullable = false, length = 30)
    private String estado;

    @Column(length = 1)
    private String atrdisc;

    @ManyToOne
    @JoinColumn(name = "gps")
    private GPS gps;

    @ManyToOne
    @JoinColumn(name = "loja")
    private Shop loja;

    @OneToOne(mappedBy = "bicicleta")
    private EletricBicycle electrica;

    @OneToMany(mappedBy = "bicicleta")
    private List<Reservation> reservas;

    public void Bycicle() {}

    public void Bycicle(Integer id_bicicleta, Double peso, Integer raio, String modelo, String marca, Integer mudanca, String estado, String atrdisc, GPS gps, Shop loja, EletricBicycle electrica, List<Reservation> reservas) {
        this.id_bicicleta = id_bicicleta;
        this.peso = peso;
        this.raio = raio;
        this.modelo = modelo;
        this.marca = marca;
        this.mudanca = mudanca;
        this.estado = estado;
        this.atrdisc = atrdisc;
        this.gps = gps;
        this.loja = loja;
        this.electrica = electrica;
        this.reservas = reservas;
    }



    public Integer getId_bicicleta() {
        return id_bicicleta;
    }


    public void setId_bicicleta(Integer id_bicicleta) {
        this.id_bicicleta = id_bicicleta;
    }


    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Integer getRaio() {
        return raio;
    }

    public void setRaio(Integer raio) {
        this.raio = raio;
    }


    public String getModelo() {
        return modelo;
    }


    public void setModelo(String modelo) {
        this.modelo = modelo;
    }


    public String getMarca() {
        return marca;
    }


    public void setMarca(String marca) {
        this.marca = marca;
    }


    public Integer getMudanca() {
        return mudanca;
    }


    public void setMudanca(Integer mudanca) {
        this.mudanca = mudanca;
    }


    public String getEstado() {
        return estado;
    }


    public void setEstado(String estado) {
        this.estado = estado;
    }


    public String getAtrdisc() {
        return atrdisc;
    }


    public void setAtrdisc(String atrdisc) {
        this.atrdisc = atrdisc;
    }


    public GPS getGps() {
        return gps;
    }

    public void setGps(GPS gps) {
        this.gps = gps;
    }

    public Shop getLoja() {
        return loja;
    }

    public void setLoja(Shop loja) {
        this.loja = loja;
    }

    public EletricBicycle getElectrica() {
        return electrica;
    }

    public void setElectrica(EletricBicycle electrica) {
        this.electrica = electrica;
    }

    public List<Reservation> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reservation> reservas) {
        this.reservas = reservas;
    }
}
