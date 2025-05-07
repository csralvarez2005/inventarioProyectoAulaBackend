package inventarioEquiposBackend.controller;

import inventarioEquiposBackend.dto.FuncionarioDTO;
import inventarioEquiposBackend.model.Funcionario;
import inventarioEquiposBackend.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

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
}
