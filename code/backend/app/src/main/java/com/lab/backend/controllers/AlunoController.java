package com.lab.backend.controllers;

import com.lab.backend.entities.Aluno;
import com.lab.backend.entities.dtos.AlunoCreateRequestDTO;
import com.lab.backend.entities.dtos.AlunoResponseDTO;
import com.lab.backend.mappers.AlunoMapper;
import com.lab.backend.services.AlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> findById(@PathVariable Long id) {
        Aluno aluno = alunoService.findById(id);
        return ResponseEntity.ok(AlunoMapper.INSTANCE.toResponseDTO(aluno));
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponseDTO>> findAll() {
        return ResponseEntity.ok(alunoService.findAll()
                .stream()
                .map(AlunoMapper.INSTANCE::toResponseDTO)
                .toList());
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
