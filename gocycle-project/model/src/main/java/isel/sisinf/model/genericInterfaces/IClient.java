package isel.sisinf.model.genericInterfaces;

public interface IClient {
    Integer getCode();
    String getName();
    String getAddress();
    String getPhoneNumber();
    String getCC();
    String getPassport();
    String getCitizenship();

    void setCode(Integer code);

    void setName(String name);

    void setAddress(String address);

    void setPhoneNumber(String phoneNumber);

    void setCC(String CC);

    void setPassport(String passport);

    void serCitizenship(String citizenship);
}
