package inventarioEquiposBackend.model;

public class LoginRequest {
    private String identificacion;
    private String password;

    // Getters and Setters
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
