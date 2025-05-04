package inventarioEquiposBackend.controller;

import inventarioEquiposBackend.dto.EquipoDTO;
import inventarioEquiposBackend.model.Equipo;
import inventarioEquiposBackend.service.EquipoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {
    private final EquipoService equipoService;

    @Autowired
    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    // Crear un nuevo equipo
    @PostMapping
    public ResponseEntity<Equipo> crearEquipo(@Valid @RequestBody EquipoDTO equipoDTO) {
        Equipo nuevoEquipo = equipoService.crearEquipo(equipoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEquipo);
    }

    // Obtener un equipo por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Equipo> obtenerEquipo(@PathVariable Long id) {
        Equipo equipo = equipoService.obtenerEquipoPorId(id);
        return ResponseEntity.ok(equipo);
    }

    // Obtener todos los equipos
    @GetMapping
    public ResponseEntity<List<Equipo>> obtenerTodosLosEquipos() {
        List<Equipo> equipos = equipoService.obtenerTodosLosEquipos();
        return ResponseEntity.ok(equipos);
    }

    // Actualizar un equipo existente
    @PutMapping("/{id}")
    public ResponseEntity<Equipo> actualizarEquipo(@PathVariable Long id, @Valid @RequestBody EquipoDTO equipoDTO) {
        Equipo equipoActualizado = equipoService.actualizarEquipo(id, equipoDTO);
        return ResponseEntity.ok(equipoActualizado);
    }

    // Eliminar un equipo por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEquipo(@PathVariable Long id) {
        equipoService.eliminarEquipo(id);
        return ResponseEntity.noContent().build();
    }
}
