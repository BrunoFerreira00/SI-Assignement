package isel.sisinf.model.genericInterfaces;

public interface IReservation {
    Integer getCode();
    String getInitialDate();
    String getFinalDate();
    Integer getBicycleCode();
    Integer getPrice();

    void setCode();
    void setInitialDate();
    void setFinalDate();
    void setBicycleCode();
    void setPrice();
}
