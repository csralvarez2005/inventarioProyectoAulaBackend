package inventarioEquiposBackend.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileController {
    @GetMapping("/uploads/funcionarios/{filename:.+}")
    public ResponseEntity<Resource> verImagen(@PathVariable String filename) {
        try {
            // Ruta completa de las imágenes
            Path path = Paths.get("uploads/funcionarios").resolve(filename);
            Resource imagen = new FileSystemResource(path);

            if (!imagen.exists()) {
                return ResponseEntity.notFound().build();
            }

            String contentType = Files.probeContentType(path);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + imagen.getFilename() + "\"")
                    .body(imagen);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
