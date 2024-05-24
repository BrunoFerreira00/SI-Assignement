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

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCC(String CC) {
        this.CC = CC;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public void setCitizenship(String citizenship){
        this.citizenship = citizenship;
    }
}
