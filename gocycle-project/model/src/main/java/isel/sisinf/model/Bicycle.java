package isel.sisinf.model;

public class Bicycle implements IBycicle {
    private String type;
    private Integer code;
    private Integer weight;
    private String model;
    private String brand;
    private Integer changeSystem; // Can only be {1, 6, 18, 24}
    private String state; // Can only be {livre, ocupado, reserva, em manutenção}
    private String GPS;

    public Bicycle(String type, Integer code, Integer weight, String model, String brand, Integer changeSystem, String state, String GPS) {
        this.type = type;
        this.code = code;
        this.weight = weight;
        this.model = model;
        this.brand = brand;
        this.changeSystem = changeSystem;
        this.state = state;
        this.GPS = GPS;
    }

    public String getType() {
        return type;
    }

    public Integer getCode() {
        return code;
    }

    public Integer getWeight() {
        return weight;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public Integer getChangeSystem() {
        return changeSystem;
    }

    public String getState() {
        return state;
    }

    public String getGPS() {
        return GPS;
    }

    public void setType(String type) {
        this.type = type;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public void setChangeSystem(Integer changeSystem) {
        this.changeSystem = changeSystem;
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setGPS(String GPS) {
        this.GPS = GPS;
    }
}
