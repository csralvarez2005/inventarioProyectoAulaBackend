package inventarioEquiposBackend.service.impl;

import inventarioEquiposBackend.dto.FuncionarioDTO;
import inventarioEquiposBackend.exception.DuplicateResourceException;
import inventarioEquiposBackend.exception.ResourceNotFoundException;
import inventarioEquiposBackend.model.Funcionario;

import inventarioEquiposBackend.model.Rol;
import inventarioEquiposBackend.repository.FuncionarioRepository;
import inventarioEquiposBackend.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class FuncionarioServiceImpl implements FuncionarioService {
    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Funcionario create(FuncionarioDTO dto) {
        if (repository.findByIdentificacion(dto.getIdentificacion()).isPresent()) {
            throw new DuplicateResourceException("Identificación ya registrada");
        }

        Funcionario f = mapToEntity(dto);
        f.setEstado("activo");

        // Asegurarse de que la contraseña no sea null antes de codificarla
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            f.setPassword(passwordEncoder.encode(dto.getPassword()));
        } else {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }

        return repository.save(f);
    }

    @Override
    public Funcionario getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario no encontrado"));
    }

    @Override
    public List<Funcionario> getAll() {
        return repository.findAll();
    }

    @Override
    public Funcionario update(Long id, FuncionarioDTO dto) {
        Funcionario f = getById(id);

        if (!f.getIdentificacion().equals(dto.getIdentificacion()) &&
                repository.findByIdentificacion(dto.getIdentificacion()).isPresent()) {
            throw new DuplicateResourceException("Identificación ya registrada");
        }

        // Mantener la contraseña actual si no se proporciona una nueva
        String password = f.getPassword();
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            password = passwordEncoder.encode(dto.getPassword());
        }

        Funcionario updated = mapToEntity(dto);
        updated.setId(id);
        updated.setPassword(password);

        // Mantener el estado actual si no se proporciona uno nuevo
        if (dto.getEstado() == null || dto.getEstado().isEmpty()) {
            updated.setEstado(f.getEstado());
        }

        return repository.save(updated);
    }

    @Override
    public void delete(Long id) {
        Funcionario f = getById(id);
        repository.delete(f);
    }

    @Override
    public Funcionario getByIdentificacion(String identificacion) {
        return repository.findByIdentificacion(identificacion)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario no encontrado con identificación: " + identificacion));
    }

    @Override
    public boolean existsByIdentificacion(String identificacion) {
        return repository.existsByIdentificacion(identificacion);
    }

    // Nuevo método para actualizar solo la imagen del funcionario
    public Funcionario updateImagen(Long id, String imagenUrl) {
        Funcionario funcionario = getById(id);
        funcionario.setImagen_url(imagenUrl);
        return repository.save(funcionario);
    }

    private Funcionario mapToEntity(FuncionarioDTO dto) {
        Funcionario f = new Funcionario();
        f.setTipo_documento(dto.getTipo_documento());
        f.setApellido_funcionario(dto.getApellido_funcionario());
        f.setCargo(dto.getCargo());
        f.setCelular(dto.getCelular());
        f.setDireccion(dto.getDireccion());
        f.setEmail(dto.getEmail());
        f.setEstado(dto.getEstado());
        f.setEstado_civil(dto.getEstado_civil());
        f.setFecha_nacimiento(dto.getFecha_nacimiento());
        f.setGenero(dto.getGenero());
        f.setIdentificacion(dto.getIdentificacion());
        f.setNombre_funcionario(dto.getNombre_funcionario());

        // Añadir la URL de la imagen
        f.setImagen_url(dto.getImagen_url());

        // Conversión segura de String a Enum con manejo de errores
        try {
            if (dto.getRol() != null) {
                f.setRol(Rol.valueOf(dto.getRol().toUpperCase()));
            } else {
                // Establecer un rol por defecto si es null
                f.setRol(Rol.USER);
            }
        } catch (IllegalArgumentException e) {
            // Si el valor no coincide con ningún valor del enum, establecer un valor por defecto
            f.setRol(Rol.USER);
        }

        return f;
    }
}