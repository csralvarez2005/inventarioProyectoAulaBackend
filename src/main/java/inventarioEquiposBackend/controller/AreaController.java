package inventarioEquiposBackend.controller;

import inventarioEquiposBackend.dto.AreaDTO;
import inventarioEquiposBackend.model.Area;
import inventarioEquiposBackend.service.AreaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/areas")
public class AreaController {
    private final AreaService areaService;

    @Autowired
    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @PostMapping
    public ResponseEntity<Area> createArea(@Valid @RequestBody AreaDTO areaDTO) {
        Area createdArea = areaService.createArea(areaDTO);
        return new ResponseEntity<>(createdArea, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Area> getAreaById(@PathVariable Long id) {
        Area area = areaService.getAreaById(id);
        return ResponseEntity.ok(area);
    }

    @GetMapping
    public ResponseEntity<List<Area>> getAllAreas() {
        List<Area> areas = areaService.getAllAreas();
        return ResponseEntity.ok(areas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Area> updateArea(@PathVariable Long id, @Valid @RequestBody AreaDTO areaDTO) {
        Area updatedArea = areaService.updateArea(id, areaDTO);
        return ResponseEntity.ok(updatedArea);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArea(@PathVariable Long id) {
        areaService.deleteArea(id);
        return ResponseEntity.noContent().build();
    }
}
