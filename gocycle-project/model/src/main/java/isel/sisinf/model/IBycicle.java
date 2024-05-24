package isel.sisinf.model;

public interface IBycicle {
    String getType();
    Integer getCode();
    Integer getWeight();
    String getModel();
    String getBrand();
    Integer getChangeSystem();
    String getState();
    String getGPS();

    void setType(String type);
    void setCode(Integer code);
    void setWeight(Integer weight);
    void setModel(String model);
    void setBrand(String brand);
    void setChangeSystem(Integer changeSystem);
    void setState(String state);
    void setGPS(String GPS);


}
