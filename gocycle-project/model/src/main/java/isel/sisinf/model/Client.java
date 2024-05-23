package isel.sisinf.model;

public class Client {
    private Integer code;
    private String name;
    private String address;
    private String phoneNumber;
    private String CC;
    private String passport;
    private String citizenship;


    public Client(Integer code, String name, String address, String phoneNumber, String CC, String passport, String citizenship) {
        this.code = code;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.CC = CC;
        this.passport = passport;
        this.citizenship = citizenship;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCC() {
        return CC;
    }

    public String getPassport() {
        return passport;
    }

    public String getCitizenship() {
        return citizenship;
    }

}
