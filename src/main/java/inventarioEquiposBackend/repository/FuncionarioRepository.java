package inventarioEquiposBackend.repository;

import inventarioEquiposBackend.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    // Para autenticación por identificador (email, username, etc.)
    Optional<Funcionario> findByEmail(String email);

    // También puedes mantener la búsqueda por identificación si es útil
    Optional<Funcionario> findByIdentificacion(String identificacion);

    boolean existsByEmail(String email);
}
