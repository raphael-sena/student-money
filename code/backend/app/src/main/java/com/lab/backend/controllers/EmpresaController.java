package com.lab.backend.controllers;

import com.lab.backend.entities.dtos.EmpresaCreateRequestDTO;
import com.lab.backend.entities.dtos.EmpresaResponseDTO;
import com.lab.backend.services.EmpresaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(empresaService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<EmpresaResponseDTO>> findAll() {
        return ResponseEntity.ok(empresaService.findAll());
    }

    @PostMapping
    public ResponseEntity<EmpresaResponseDTO> create(@RequestBody EmpresaCreateRequestDTO obj) {
        EmpresaResponseDTO empresa = empresaService.create(obj);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(empresa.usuario().id())
                .toUri();

        return ResponseEntity.created(uri).body(empresa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        empresaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
