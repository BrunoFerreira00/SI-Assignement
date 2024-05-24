package isel.sisinf.model.genericInterfaces;

public interface IGPS {

    String getSerialNumber();
    String getLocation();
    String getBatteryPercentage();

    void setSerialNumber();
    void setLocation();
    void setBatteryPercentage();
}
