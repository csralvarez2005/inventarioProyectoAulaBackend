package inventarioEquiposBackend.service;

import inventarioEquiposBackend.dto.EquipoDTO;
import inventarioEquiposBackend.model.Equipo;

import java.util.List;

public interface EquipoService {
    Equipo crearEquipo(EquipoDTO equipoDTO);
    Equipo obtenerEquipoPorId(Long id);
    List<Equipo> obtenerTodosLosEquipos();
    Equipo actualizarEquipo(Long id, EquipoDTO equipoDTO);
    boolean eliminarEquipo(Long id);
}
