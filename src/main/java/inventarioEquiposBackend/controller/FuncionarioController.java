package inventarioEquiposBackend.controller;

import inventarioEquiposBackend.dto.FuncionarioDTO;
import inventarioEquiposBackend.model.Funcionario;
import inventarioEquiposBackend.service.FileStorageService;
import inventarioEquiposBackend.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @Autowired
    private FileStorageService fileStorageService;

    // ✅ Crear funcionario con imagen
    @PostMapping
    public ResponseEntity<?> createFuncionario(
            @RequestParam("nombre_funcionario") String nombre,
            @RequestParam("apellido_funcionario") String apellido,
            @RequestParam("identificacion") String identificacion,
            @RequestParam("cargo") String cargo,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam(value = "imagen", required = false) MultipartFile imagen
    ) {
        try {
            FuncionarioDTO dto = new FuncionarioDTO();
            dto.setNombre_funcionario(nombre);
            dto.setApellido_funcionario(apellido);
            dto.setIdentificacion(identificacion);
            dto.setCargo(cargo);
            dto.setEmail(email);
            dto.setPassword(password);
            dto.setEstado("activo");

            if (imagen != null && !imagen.isEmpty()) {
                if (!imagen.getContentType().startsWith("image/")) {
                    return ResponseEntity.badRequest().body("El archivo debe ser una imagen válida");
                }
                String url = fileStorageService.storeFile(imagen, identificacion);
                dto.setImagen_url(url);
            }

            Funcionario creado = service.create(dto);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    Map.of(
                            "mensaje", "Funcionario creado correctamente",
                            "funcionario", creado,
                            "imagen_url", creado.getImagen_url()
                    )
            );
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la imagen: " + e.getMessage());
        }
    }

    // ✅ Subir o actualizar solo la imagen
    @PutMapping("/{id}/imagen")
    public ResponseEntity<?> uploadImage(@PathVariable Long id,
                                         @RequestParam("imagen") MultipartFile imagen) {
        try {
            if (!imagen.getContentType().startsWith("image/")) {
                return ResponseEntity.badRequest().body("El archivo debe ser una imagen válida");
            }

            String imagenUrl = fileStorageService.storeFile(imagen, "funcionario_" + id);
            Funcionario actualizado = service.updateImagen(id, imagenUrl);
            return ResponseEntity.ok(actualizado);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al subir imagen: " + e.getMessage());
        }
    }

    // ✅ Ver imagen
    @GetMapping("/uploads/{filename:.+}")
    public ResponseEntity<Resource> verImagen(@PathVariable String filename) {
        try {
            // Ruta completa donde están guardadas las imágenes
            Path path = Paths.get("uploads/funcionarios").resolve(filename);

            // Verifica si el archivo existe
            Resource imagen = new FileSystemResource(path);
            if (!imagen.exists()) {
                return ResponseEntity.notFound().build();
            }

            // Detecta el tipo de contenido
            String contentType = Files.probeContentType(path);
            if (contentType == null) {
                contentType = "application/octet-stream";  // Tipo por defecto si no se encuentra el tipo
            }

            // Devuelve la imagen con los encabezados adecuados
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + imagen.getFilename() + "\"")
                    .body(imagen);
        } catch (Exception e) {
            // En caso de error, retorna 404
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Obtener perfil del funcionario autenticado
    @GetMapping("/perfil")
    public ResponseEntity<Funcionario> getPerfilFuncionario() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Funcionario funcionario = service.getByIdentificacion(username);
        return ResponseEntity.ok(funcionario);
    }

    // ✅ Listar todos los funcionarios
    @GetMapping
    public ResponseEntity<List<Funcionario>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // ✅ Buscar por ID
    @GetMapping("/id/{id}")
    public ResponseEntity<Funcionario> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // ✅ Eliminar funcionario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Funcionario eliminado correctamente");
    }
}



