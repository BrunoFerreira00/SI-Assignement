package isel.sisinf.model;


import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "loja")
public class Shop {
    @Id
    private Integer codigo;

    @Column(nullable = false, length = 40, unique = true)
    private String email;

    @Column(nullable = false, length = 100)
    private String endereco;

    @Column(nullable = false, length = 30)
    private String localidade;

    @Column(nullable = false)
    private String notelefone;

    @ManyToOne
    @JoinColumn(name = "gestor", nullable = false)
    private Client gestor;

    @OneToMany(mappedBy = "loja")
    private Set<Bicycle> bicicletas;

    @OneToMany(mappedBy = "loja")
    private Set<Reservation> reservas;

    public void Shop() {}

    public void Shop(Integer codigo, String email, String endereco, String localidade, String notelefone, Client gestor, Set<Bicycle> bicicletas, Set<Reservation> reservas) {
        this.codigo = codigo;
        this.email = email;
        this.endereco = endereco;
        this.localidade = localidade;
        this.notelefone = notelefone;
        this.gestor = gestor;
        this.bicicletas = bicicletas;
        this.reservas = reservas;
    }


    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getNotelefone() {
        return notelefone;
    }

    public void setNotelefone(String notelefone) {
        this.notelefone = notelefone;
    }

    public Client getGestor() {
        return gestor;
    }

    public void setGestor(Client gestor) {
        this.gestor = gestor;
    }

    public Set<Bicycle> getBicicletas() {
        return bicicletas;
    }

    public void setBicicletas(Set<Bicycle> bicicletas) {
        this.bicicletas = bicicletas;
    }

    public Set<Reservation> getReservas() {
        return reservas;
    }

    public void setReservas(Set<Reservation> reservas) {
        this.reservas = reservas;
    }
}
