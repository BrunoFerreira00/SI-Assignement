package isel.sisinf.model;

import jakarta.persistence.*;

@Entity(name = "gps")
public class GPS {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private String serialNumber;
    @Column(nullable = false, precision = 6, scale = 4)
    private Double latitude;

    @Column(nullable = false, precision = 6, scale = 4)
    private Double longitude;

    @Column(nullable = false)
    private String batteryPercentage;

    public void GPS() {}

    public void GPS(String serialNumber, Double latitude, Double longitude, String batteryPercentage) {
        this.serialNumber = serialNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.batteryPercentage = batteryPercentage;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

     public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getBatteryPercentage() {
        return batteryPercentage;
    }

    public void setSerialNumber(String serialNumber){
        this.serialNumber = serialNumber;
    }

    public void setLatitude(Double latitude){
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude){
        this.longitude = longitude;
    }

    public void setBatteryPercentage(String batteryPercentage){
        this.batteryPercentage = batteryPercentage;
    }
}
