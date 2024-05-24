package isel.sisinf.model;

public class GPS {
    private String serialNumber;
    private String location; // Might be private String latitude and longitude;

    private String batteryPercentage;

    public GPS(String serialNumber, String location, String batteryPercentage) {
        this.serialNumber = serialNumber;
        this.location = location;
        this.batteryPercentage = batteryPercentage;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getLocation() {
        return location;
    }

    public String getBatteryPercentage() {
        return batteryPercentage;
    }

    public void setSerialNumber(String serialNumber){
        this.serialNumber = serialNumber;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public void setBatteryPercentage(String batteryPercentage){
        this.batteryPercentage = batteryPercentage;
    }
}
