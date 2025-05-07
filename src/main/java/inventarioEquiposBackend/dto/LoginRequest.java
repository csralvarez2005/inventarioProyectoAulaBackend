package inventarioEquiposBackend.dto;

public class LoginRequest {
    private String identificacion;
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String identificacion, String password) {
        this.identificacion = identificacion;
        this.password = password;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
