package isel.sisinf.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "loja")
public class Shop {
    @Id
    private Integer code;
    private String manager;
    private String address;
    private String locality;
    private String phoneNumber;
    private String email;
/*
    public Shop(Integer code, String manager, String address, String locality, String phoneNumber, String email) {
        this.code = code;
        this.manager = manager;
        this.address = address;
        this.locality = locality;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

 */

    public Integer getCode() {
        return code;
    }

    public String getManager() {
        return manager;
    }

    public String getAddress() {
        return address;
    }

    public String getLocality() {
        return locality;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setCode(Integer code){
        this.code = code;
    }
    public void setManager(String manager){
        this.manager = manager;
    }
    public void setAddress(String address){
        this.address = address;
    }

    public void setLocality(String locality){
        this.locality = locality;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email){
        this.email = email;
    }

    @ManyToOne(optional = false)
    private Bycicle bycicles;

    public Bycicle getBycicles() {
        return bycicles;
    }

    public void setBycicles(Bycicle bycicles) {
        this.bycicles = bycicles;
    }
}
