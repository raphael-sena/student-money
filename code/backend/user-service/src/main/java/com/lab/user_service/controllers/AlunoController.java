package com.lab.user_service.controllers;

import com.lab.user_service.entities.Aluno;
import com.lab.user_service.entities.dtos.AlunoCreateRequestDTO;
import com.lab.user_service.entities.dtos.AlunoResponseDTO;
import com.lab.user_service.mappers.AlunoMapper;
import com.lab.user_service.services.AlunoService;
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

    @PatchMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> update(@PathVariable Long id, @RequestBody AlunoCreateRequestDTO obj) {
        AlunoResponseDTO aluno = alunoService.update(id, obj);
        return ResponseEntity.ok(aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        alunoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
