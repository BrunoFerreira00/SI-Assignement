package isel.sisinf.model.genericInterfaces;

import isel.sisinf.model.EletricBicycle;
import isel.sisinf.model.GPS;
import isel.sisinf.model.Reservation;
import isel.sisinf.model.Shop;

import java.util.List;

public interface IBycicle {
    Integer getId();
    Double getWeight();
    Integer getRadius();
    String getModel();
    String getBrand();
    Integer getGear();
    String getState();
    String getDisc();
    GPS getGps();
    Shop getShop();
    EletricBicycle getElectric();
    List<Reservation> getReservations();
    void setId(Integer id);
    void setWeight(Double weight);
    void setRadius(Integer radius);
    void setModel(String model);
    void setBrand(String brand);
    void setGear(Integer gear);
    void setState(String state);
    void setDisc(String disc);
    void setGps(GPS gps);
    void setShop(Shop shop);
    void setElectric(EletricBicycle electric);
    void setReservations(List<Reservation> reservations);


}
