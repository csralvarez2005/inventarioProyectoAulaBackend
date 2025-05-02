package inventarioEquiposBackend.controller;

import inventarioEquiposBackend.dto.FuncionarioDTO;
import inventarioEquiposBackend.model.Funcionario;
import inventarioEquiposBackend.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @PostMapping
    public Funcionario create(@RequestBody FuncionarioDTO dto) {
        return service.create(dto);
    }

    @GetMapping("/{id}")
    public Funcionario getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<Funcionario> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public Funcionario update(@PathVariable Long id, @RequestBody FuncionarioDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
