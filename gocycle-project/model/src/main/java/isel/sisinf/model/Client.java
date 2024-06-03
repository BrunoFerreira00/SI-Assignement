package isel.sisinf.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "pessoa")
public class Client {
    @Id
    private Integer id;
    private String nome;
    private String morada;
    private String email;
    private String telefone;
    private String noident;
    private String nacionalidade;

    private String atrdisc;

    public void Client() {}

    public void Client(Integer id, String nome, String morada, String email, String telefone, String noident, String nacionalidade, String atrdisc) {
        this.id = id;
        this.nome = nome;
        this.morada = morada;
        this.email = email;
        this.telefone = telefone;
        this.noident = noident;
        this.nacionalidade = nacionalidade;
        this.atrdisc = atrdisc;
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




}
