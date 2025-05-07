package inventarioEquiposBackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

public class FuncionarioDTO {
    @NotBlank
    private String tipo_documento;

    @NotBlank
    private String apellido_funcionario;

    @NotBlank
    private String cargo;

    @NotBlank
    private String celular;

    @NotBlank
    private String direccion;

    @Email
    @NotBlank
    private String email;

    private String estado = "activo"; // opcional con valor por defecto

    private String estado_civil;

    private Date fecha_nacimiento;

    private String genero;

    @NotBlank
    private String identificacion;

    @NotBlank
    private String nombre_funcionario;

    @NotBlank
    private String password;

    @NotBlank(message = "El rol es obligatorio (ADMIN o USER)")
    @Pattern(regexp = "ADMIN|USER", flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "El rol debe ser ADMIN o USER")
    private String rol;

    // Getters y Setters
    // ...

    // Métodos generados (puedes usar Lombok si prefieres)
    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getApellido_funcionario() {
        return apellido_funcionario;
    }

    public void setApellido_funcionario(String apellido_funcionario) {
        this.apellido_funcionario = apellido_funcionario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre_funcionario() {
        return nombre_funcionario;
    }

    public void setNombre_funcionario(String nombre_funcionario) {
        this.nombre_funcionario = nombre_funcionario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
