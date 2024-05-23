package isel.sisinf.model;

public class Reservation {
    private Integer code;
    private String initialDate;
    private String finalDate;
    private Integer bicycleCode;
    private Integer price;

    public Reservation(Integer code, String initialDate, String finalDate, Integer bicycleCode, Integer price) {
        this.code = code;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.bicycleCode = bicycleCode;
        this.price = price;
    }

    public Integer getCode() {
        return code;
    }

    public String getInitialDate() {
        return initialDate;
    }

    public String getFinalDate() {
        return finalDate;
    }

    public Integer getBicycleCode() {
        return bicycleCode;
    }

    public Integer getPrice() {
        return price;
    }

}
