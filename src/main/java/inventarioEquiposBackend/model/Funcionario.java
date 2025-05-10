package inventarioEquiposBackend.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "funcionarios")
public class Funcionario implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo_documento;
    private String apellido_funcionario;
    private String cargo;
    private String celular;
    private String direccion;
    private String email;

    @Column(nullable = false)
    private String estado = "activo"; // valor por defecto

    private String estado_civil;
    private Date fecha_nacimiento;
    private String genero;

    @Column(unique = true)
    private String identificacion;

    private String nombre_funcionario;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;
    // Nuevo campo para la URL de la imagen
    private String imagen_url;

    // Getters y Setters

    public String getImagen_url() {
        return imagen_url;
    }

    public void setImagen_url(String imagen_url) {
        this.imagen_url = imagen_url;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTipo_documento() { return tipo_documento; }

    public void setTipo_documento(String tipo_documento) { this.tipo_documento = tipo_documento; }

    public String getApellido_funcionario() { return apellido_funcionario; }

    public void setApellido_funcionario(String apellido_funcionario) { this.apellido_funcionario = apellido_funcionario; }

    public String getCargo() { return cargo; }

    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getCelular() { return celular; }

    public void setCelular(String celular) { this.celular = celular; }

    public String getDireccion() { return direccion; }

    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }

    public String getEstado_civil() { return estado_civil; }

    public void setEstado_civil(String estado_civil) { this.estado_civil = estado_civil; }

    public Date getFecha_nacimiento() { return fecha_nacimiento; }

    public void setFecha_nacimiento(Date fecha_nacimiento) { this.fecha_nacimiento = fecha_nacimiento; }

    public String getGenero() { return genero; }

    public void setGenero(String genero) { this.genero = genero; }

    public String getIdentificacion() { return identificacion; }

    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }

    public String getNombre_funcionario() { return nombre_funcionario; }

    public void setNombre_funcionario(String nombre_funcionario) { this.nombre_funcionario = nombre_funcionario; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    // Métodos de UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + rol.name()));
    }

    @Override
    public String getUsername() {
        return identificacion; // o puedes usar email si prefieres
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return estado.equalsIgnoreCase("activo");
    }


}
