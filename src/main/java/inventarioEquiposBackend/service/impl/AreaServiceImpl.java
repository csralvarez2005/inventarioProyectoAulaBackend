package inventarioEquiposBackend.service.impl;


import inventarioEquiposBackend.dto.AreaDTO;
import inventarioEquiposBackend.exception.DuplicateResourceException;
import inventarioEquiposBackend.exception.ResourceNotFoundException;
import inventarioEquiposBackend.model.Area;
import inventarioEquiposBackend.repository.AreaRepository;
import inventarioEquiposBackend.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AreaServiceImpl implements AreaService {
    private final AreaRepository areaRepository;

    @Autowired
    public AreaServiceImpl(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    @Override
    public Area createArea(AreaDTO areaDTO) {
        // Verificar si ya existe un área con el mismo nombre
        if (areaRepository.existsByNombre(areaDTO.getNombre())) {
            throw new DuplicateResourceException("Ya existe un área con el nombre: " + areaDTO.getNombre());
        }

        // Generar ID consecutivo
        Long nextId = generateNextId();

        // Crear nueva área
        Area area = new Area();
        area.setId(nextId);
        area.setNombre(areaDTO.getNombre());
        area.setTipo(areaDTO.getTipo());

        return areaRepository.save(area);
    }

    @Override
    public Area getAreaById(Long id) {
        return areaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el área con ID: " + id));
    }

    @Override
    public List<Area> getAllAreas() {
        return areaRepository.findAll();
    }

    @Override
    public Area updateArea(Long id, AreaDTO areaDTO) {
        Area existingArea = getAreaById(id);

        // Verificar si el nuevo nombre ya existe y no es el mismo área
        if (!existingArea.getNombre().equals(areaDTO.getNombre()) &&
                areaRepository.existsByNombre(areaDTO.getNombre())) {
            throw new DuplicateResourceException("Ya existe un área con el nombre: " + areaDTO.getNombre());
        }

        existingArea.setNombre(areaDTO.getNombre());
        existingArea.setTipo(areaDTO.getTipo());

        return areaRepository.save(existingArea);
    }

    @Override
    public void deleteArea(Long id) {
        if (!areaRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se encontró el área con ID: " + id);
        }
        areaRepository.deleteById(id);
    }

    // Método para generar IDs consecutivos
    public Long generateNextId() {
        List<Area> areas = areaRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        if (!areas.isEmpty()) {
            return areas.get(0).getId() + 1;
        }
        return 1L;
    }
}
