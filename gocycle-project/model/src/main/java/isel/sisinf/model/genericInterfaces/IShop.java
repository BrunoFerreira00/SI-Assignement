package isel.sisinf.model.genericInterfaces;

public interface IShop {
    Integer getCode();
    String getManager();
    String getAddress();
    String getLocality();
    String getPhoneNumber();
    String getEmail();

    void setCode();
    void setManager();
    void setAddress();
    void setLocality();
    void setPhoneNumber();
    void setEmail();

}
