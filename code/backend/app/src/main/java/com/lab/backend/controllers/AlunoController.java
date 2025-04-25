package com.lab.backend.controllers;

import com.lab.backend.entities.dtos.AlunoCreateRequestDTO;
import com.lab.backend.entities.dtos.AlunoResponseDTO;
import com.lab.backend.services.AlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public ResponseEntity<AlunoResponseDTO> create(@RequestBody AlunoCreateRequestDTO obj) {
        AlunoResponseDTO aluno = alunoService.create(obj);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(aluno.pessoa().usuario().id())
                .toUri();

        return ResponseEntity.created(uri).body(aluno);
    }
}
