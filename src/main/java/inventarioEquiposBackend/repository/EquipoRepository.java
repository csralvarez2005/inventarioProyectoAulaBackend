package inventarioEquiposBackend.repository;

import inventarioEquiposBackend.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    boolean existsByNombreEquipo(String nombreEquipo);


}
