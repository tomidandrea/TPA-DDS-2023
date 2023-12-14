package Presentacion.dto;

public class LoginResponse {

    private String idSesion;
    private String rol;

    public LoginResponse(String idSesion, String rol) {
        this.idSesion = idSesion;
        this.rol = rol;
    }

    public String getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(String idSesion) {
        this.idSesion = idSesion;
    }
}