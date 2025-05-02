package inventarioEquiposBackend.repository;

import inventarioEquiposBackend.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByIdentificacion(String identificacion);
}
