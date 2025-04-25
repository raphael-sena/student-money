package com.lab.backend.controllers;

import com.lab.backend.entities.dtos.EmpresaCreateRequestDTO;
import com.lab.backend.entities.dtos.EmpresaResponseDTO;
import com.lab.backend.services.EmpresaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
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
}
