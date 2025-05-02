package inventarioEquiposBackend.service;

import inventarioEquiposBackend.dto.FuncionarioDTO;
import inventarioEquiposBackend.model.Funcionario;

import java.util.List;

public interface FuncionarioService {
    Funcionario create(FuncionarioDTO dto);
    Funcionario getById(Long id);
    List<Funcionario> getAll();
    Funcionario update(Long id, FuncionarioDTO dto);
    void delete(Long id);
}
