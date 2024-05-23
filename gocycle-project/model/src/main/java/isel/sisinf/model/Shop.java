package isel.sisinf.model;

public class Shop {
    private Integer code;
    private String manager;
    private String address;
    private String locality;
    private String phoneNumber;
    private String email;

    public Shop(Integer code, String manager, String address, String locality, String phoneNumber, String email) {
        this.code = code;
        this.manager = manager;
        this.address = address;
        this.locality = locality;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

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

}
