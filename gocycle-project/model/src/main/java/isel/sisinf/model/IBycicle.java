package isel.sisinf.model;

public interface IBycicle {
    String getAtrdisc();
    Integer getId_bicicleta();
    Integer getPeso();
    String getModelo();
    String getMarca();
    Integer getMudanca();
    String getEstado();
    String getGps();

    void setAtrdisc(String atrdisc);
    void setId_bicicleta(Integer id_bicicleta);
    void setPeso(Integer peso);
    void setModelo(String modelo);
    void setMarca(String marca);
    void setMudanca(Integer mudanca);
    void setEstado(String estado);
    void setGps(String gps);


}
