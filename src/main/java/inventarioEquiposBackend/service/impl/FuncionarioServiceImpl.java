package inventarioEquiposBackend.service.impl;

import inventarioEquiposBackend.dto.FuncionarioDTO;
import inventarioEquiposBackend.exception.DuplicateResourceException;
import inventarioEquiposBackend.exception.ResourceNotFoundException;
import inventarioEquiposBackend.model.Funcionario;
import inventarioEquiposBackend.repository.FuncionarioRepository;
import inventarioEquiposBackend.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FuncionarioServiceImpl implements FuncionarioService {
    @Autowired
    private FuncionarioRepository repository;

    @Override
    public Funcionario create(FuncionarioDTO dto) {
        if (repository.findByIdentificacion(dto.getIdentificacion()).isPresent()) {
            throw new DuplicateResourceException("Identificación ya registrada");
        }

        Funcionario f = mapToEntity(dto);
        f.setEstado("activo"); // establecer estado por defecto
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
        f = mapToEntity(dto);
        f.setId(id);
        return repository.save(f);
    }

    @Override
    public void delete(Long id) {
        Funcionario f = getById(id);
        repository.delete(f);
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
        return f;
    }
}
