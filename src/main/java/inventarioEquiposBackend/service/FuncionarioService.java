package inventarioEquiposBackend.service;

import inventarioEquiposBackend.dto.FuncionarioDTO;
import inventarioEquiposBackend.model.Funcionario;

import java.util.List;

public interface FuncionarioService {

    // CRUD básico
    Funcionario create(FuncionarioDTO dto);
    Funcionario getById(Long id);
    List<Funcionario> getAll();
    Funcionario update(Long id, FuncionarioDTO dto);
    void delete(Long id);

    // Autenticación / Utilitarios
    Funcionario getByIdentificacion(String identificacion);
    boolean existsByIdentificacion(String identificacion);
    // Nuevo método para actualizar la imagen
    Funcionario updateImagen(Long id, String imagenUrl);
}
