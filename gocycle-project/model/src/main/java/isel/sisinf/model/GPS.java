package isel.sisinf.model;

import jakarta.persistence.*;

@Entity(name = "gps")
public class GPS {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer noserie;
    @Column(nullable = false, precision = 6, scale = 4)
    private Double latitude;

    @Column(nullable = false, precision = 6, scale = 4)
    private Double longitude;

    @Column(nullable = false)
    private Integer bateria;

    public void GPS() {}

    public void GPS(Integer noserie, Double latitude, Double longitude, Integer bateria) {
        this.noserie = noserie;
        this.latitude = latitude;
        this.longitude = longitude;
        this.bateria = bateria;
    }

    public Integer getNoserie() {
        return noserie;
    }

     public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Integer getBateria() {
        return bateria;
    }

    public void setNoserie(Integer noserie){
        this.noserie = noserie;
    }

    public void setLatitude(Double latitude){
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude){
        this.longitude = longitude;
    }

    public void setBateria(Integer bateria){
        this.bateria = bateria;
    }
}
