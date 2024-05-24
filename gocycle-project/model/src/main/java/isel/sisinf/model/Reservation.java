package isel.sisinf.model;

public class Reservation {
    private Integer code;
    private String initialDate;
    private String finalDate;
    private Integer bicycleCode;
    private String price;

    public Reservation(Integer code, String initialDate, String finalDate, Integer bicycleCode, String price) {
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

    public String getPrice() {
        return price;
    }

    public void setCode(Integer code){
        this.code = code;
    }

    public void setInitialDate(String initialDate){
        this.initialDate = initialDate;
    }

    public void setFinalDate(String finalDate){
        this.finalDate = finalDate;
    }
    public void setBicycleCode(Integer bicycleCode){
        this.bicycleCode = bicycleCode;
    }

    public void setPrice(String price){
        this.price = price;
    }

}
