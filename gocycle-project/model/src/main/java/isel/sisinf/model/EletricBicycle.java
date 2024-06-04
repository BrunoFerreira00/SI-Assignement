package isel.sisinf.model;

import isel.sisinf.model.genericInterfaces.IEletricBicycle;
import jakarta.persistence.*;

@Entity(name = "electrica")
public class EletricBicycle  {
    @Id
    @Column(name = "bicicleta")
    private Integer bicicletaId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "bicicleta")
    private Bicycle bicicleta;

    @Column(nullable = false)
    private Integer autonomia;

    @Column(nullable = false)
    private Integer velocidade;

    public void EletricBicycle() {}

    public void EletricBicycle(Integer bicicletaId, Bicycle bicicleta, Integer autonomia, Integer velocidade) {
        this.bicicletaId = bicicletaId;
        this.bicicleta = bicicleta;
        this.autonomia = autonomia;
        this.velocidade = velocidade;
    }

    public Integer getBicicletaId() {
        return bicicletaId;
    }

    public void setBicicletaId(Integer bicicletaId) {
        this.bicicletaId = bicicletaId;
    }

    public Bicycle getBicicleta() {
        return bicicleta;
    }

    public void setBicicleta(Bicycle bicicleta) {
        this.bicicleta = bicicleta;
    }

    public Integer getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(Integer autonomia) {
        this.autonomia = autonomia;
    }

    public Integer getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(Integer velocidade) {
        this.velocidade = velocidade;
    }
}
