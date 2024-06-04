package isel.sisinf.model;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "pessoa")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 40)
    private String nome;

    @Column(length = 150)
    private String morada;

    @Column(nullable = false, length = 40, unique = true)
    private String email;

    @Column(nullable = false, length = 30)
    private String telefone;

    @Column(nullable = false, length = 12, unique = true)
    private String noident;

    @Column(length = 20)
    private String nacionalidade;

    @Column(length = 2)
    private String atrdisc;

    @OneToMany(mappedBy = "gestor")
    private List<Shop> lojas;

    @OneToMany(mappedBy = "cliente")
    private List<ClientBooking> reservas;



    public void Client() {}

    public void Client(Integer id, String nome, String morada, String email, String telefone, String noident, String nacionalidade, String atrdisc, List<Shop> lojas, List<ClientBooking> reservas) {
        this.id = id;
        this.nome = nome;
        this.morada = morada;
        this.email = email;
        this.telefone = telefone;
        this.noident = noident;
        this.nacionalidade = nacionalidade;
        this.atrdisc = atrdisc;
        this.lojas = lojas;
        this.reservas = reservas;

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return nome;
    }

    public String getEmail() {return email;}

    public String getAddress() {
        return morada;
    }

    public String getPhoneNumber() {
        return telefone;
    }

    public String getIdentDoc() {
        return noident;
    }

    public String getAtrdisc() { return atrdisc; }


    public String getCitizenship() {
        return nacionalidade;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.nome = name;
    }

    public void setEmail(String email) {this.email = email;}

    public void setAddress(String address) {
        this.morada = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.telefone = phoneNumber;
    }

    public void setIdentDoc(String identDoc) {
        this.noident = identDoc;
    }

    public void setAtrdisc(String atrdisc) { this.atrdisc = atrdisc; }

    public void setCitizenship(String citizenship) {
        this.nacionalidade = citizenship;
    }


    public List<Shop> getLojas() {
        return lojas;
    }

    public void setLojas(List<Shop> lojas) {
        this.lojas = lojas;
    }

    public List<ClientBooking> getReservas() {
        return reservas;
    }

    public void setReservas(List<ClientBooking> reservas) {
        this.reservas = reservas;
    }
}
