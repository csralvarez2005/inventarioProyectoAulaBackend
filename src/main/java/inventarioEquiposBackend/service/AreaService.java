package inventarioEquiposBackend.service;

import inventarioEquiposBackend.dto.AreaDTO;
import inventarioEquiposBackend.model.Area;

import java.util.List;

public interface AreaService {

    Area createArea(AreaDTO areaDTO);
    Area getAreaById(Long id);
    List<Area> getAllAreas();
    Area updateArea(Long id, AreaDTO areaDTO);
    void deleteArea(Long id);
}
