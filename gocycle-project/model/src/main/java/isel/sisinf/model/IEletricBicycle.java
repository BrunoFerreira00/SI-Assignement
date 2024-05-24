package isel.sisinf.model;

public interface IEletricBicycle extends IBycicle{
    Integer getAutonomy();
    Integer getMaxSpeed();
    void setAutonomy(Integer Autonomy);
    void setMaxSpeed(Integer speed);

}
