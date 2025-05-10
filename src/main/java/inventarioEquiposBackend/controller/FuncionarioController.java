package inventarioEquiposBackend.controller;

import inventarioEquiposBackend.dto.FuncionarioDTO;
import inventarioEquiposBackend.model.Funcionario;
import inventarioEquiposBackend.service.FileStorageService;
import inventarioEquiposBackend.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

        @Autowired
        private FuncionarioService service;

        // Servicio para manejar archivos
        @Autowired
        private FileStorageService fileStorageService;

        // Endpoint específico para perfil - debe ir ANTES del endpoint con variable {id}
        @GetMapping("/perfil")
        public ResponseEntity<Funcionario> getPerfilFuncionario() {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            Funcionario funcionario = service.getByIdentificacion(username);
            return ResponseEntity.ok(funcionario);
        }

        // El POST puede quedar sin protección para permitir registrar el primer admin
        @PostMapping
        public Funcionario create(@Valid @RequestBody FuncionarioDTO dto) {
            return service.create(dto);
        }

        @PreAuthorize("hasRole('ADMIN')")
        @GetMapping("/{id}")
        public Funcionario getById(@PathVariable Long id) {
            return service.getById(id);
        }

        @PreAuthorize("hasRole('ADMIN')")
        @GetMapping
        public List<Funcionario> getAll() {
            return service.getAll();
        }

        @PreAuthorize("hasRole('ADMIN')")
        @PutMapping("/{id}")
        public Funcionario update(@PathVariable Long id, @Valid @RequestBody FuncionarioDTO dto) {
            return service.update(id, dto);
        }

        @PreAuthorize("hasRole('ADMIN')")
        @DeleteMapping("/{id}")
        public void delete(@PathVariable Long id) {
            service.delete(id);
        }

        // Nuevo endpoint para subir imagen de funcionario
        @PostMapping("/{id}/imagen")
        public ResponseEntity<?> uploadImage(@PathVariable Long id, @RequestParam("imagen") MultipartFile file) {
            try {
                // Verificar si el funcionario existe
                Funcionario funcionario = service.getById(id);

                // Guardar el archivo y obtener la URL
                String fileUrl = fileStorageService.storeFile(file, funcionario.getIdentificacion());

                // Actualizar la URL de la imagen en el funcionario
                funcionario = service.updateImagen(id, fileUrl);

                // Respuesta exitosa
                Map<String, Object> response = new HashMap<>();
                response.put("mensaje", "Imagen subida correctamente");
                response.put("imagen_url", fileUrl);

                return ResponseEntity.ok(response);
            } catch (IOException e) {
                return ResponseEntity.badRequest().body("Error al subir la imagen: " + e.getMessage());
            }
        }

        // Endpoint para actualizar solo la imagen mediante URL (opcional)
        @PutMapping("/{id}/imagen-url")
        public ResponseEntity<?> updateImageUrl(@PathVariable Long id, @RequestBody Map<String, String> request) {
            String imageUrl = request.get("imagen_url");
            if (imageUrl == null || imageUrl.isEmpty()) {
                return ResponseEntity.badRequest().body("La URL de la imagen es requerida");
            }

            Funcionario funcionario = service.updateImagen(id, imageUrl);
            return ResponseEntity.ok(funcionario);
        }
}
