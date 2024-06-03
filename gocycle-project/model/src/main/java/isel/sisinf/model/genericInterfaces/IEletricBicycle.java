package isel.sisinf.model.genericInterfaces;

import isel.sisinf.model.genericInterfaces.IBycicle;

public interface IEletricBicycle extends IBycicle {
    Integer getAutonomy();
    Integer getMaxSpeed();
    void setAutonomy(Integer Autonomy);
    void setMaxSpeed(Integer speed);

}
