package isel.sisinf.model;

public class EletricBicycle {
    private Integer autonomy;

    private Integer maxSpeed;


    public EletricBicycle(Integer autonomy, Integer maxSpeed) {
        this.autonomy = autonomy;
        this.maxSpeed = maxSpeed;
    }

    public Integer getAutonomy() {
        return autonomy;
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }


}
