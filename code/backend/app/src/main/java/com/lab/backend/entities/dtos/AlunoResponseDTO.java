package com.lab.backend.entities.dtos;

public record AlunoResponseDTO(
    PessoaResponseDTO pessoa,
    String rg,
    String curso
) {
}
