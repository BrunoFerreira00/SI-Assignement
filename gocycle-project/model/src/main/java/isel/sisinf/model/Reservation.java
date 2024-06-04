package isel.sisinf.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Entity(name = "reserva")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noreserva;

    @ManyToOne
    @JoinColumn(name = "loja", nullable = false)
    private Shop loja;

    @Column(nullable = false)
    private Timestamp dtinicio;

    @Column
    private Timestamp dtfim;

    @Column(precision = 5, scale = 2)
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "bicicleta", nullable = false)
    private Bicycle bicicleta;

    @OneToMany(mappedBy = "reserva")
    private List<ClientBooking> clientes;

    public void Reservation() {}

    public void Reservation(Integer noreserva, Shop loja, Timestamp dtinicio, Timestamp dtfim, BigDecimal valor, Bicycle bicicleta, List<ClientBooking> clientes) {
        this.noreserva = noreserva;
        this.loja = loja;
        this.dtinicio = dtinicio;
        this.dtfim = dtfim;
        this.valor = valor;
        this.bicicleta = bicicleta;
        this.clientes = clientes;
    }

    public Integer getNoreserva() {
        return noreserva;
    }

    public void setNoreserva(Integer noreserva) {
        this.noreserva = noreserva;
    }

    public Shop getLoja() {
        return loja;
    }

    public void setLoja(Shop loja) {
        this.loja = loja;
    }

    public Timestamp getDtinicio() {
        return dtinicio;
    }

    public void setDtinicio(Timestamp dtinicio) {
        this.dtinicio = dtinicio;
    }

    public Timestamp getDtfim() {
        return dtfim;
    }

    public void setDtfim(Timestamp dtfim) {
        this.dtfim = dtfim;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Bicycle getBicicleta() {
        return bicicleta;
    }

    public void setBicicleta(Bicycle bicicleta) {
        this.bicicleta = bicicleta;
    }

    public List<ClientBooking> getClientes() {
        return clientes;
    }

    public void setClientes(List<ClientBooking> clientes) {
        this.clientes = clientes;
    }
}
