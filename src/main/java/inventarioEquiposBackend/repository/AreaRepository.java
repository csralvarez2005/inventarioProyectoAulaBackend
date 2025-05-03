package inventarioEquiposBackend.repository;


import inventarioEquiposBackend.model.Area;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AreaRepository extends MongoRepository<Area, Long> {


    Optional<Area> findByNombre(String nombre);

    boolean existsByNombre(String nombre);

    @Query(value = "{}", sort = "{ id : -1 }")
    Optional<Area> findTopByOrderByIdDesc();
}
