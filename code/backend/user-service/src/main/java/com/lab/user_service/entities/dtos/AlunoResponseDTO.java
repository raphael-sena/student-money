package com.lab.user_service.entities.dtos;

public record AlunoResponseDTO(
    PessoaResponseDTO pessoa,
    String rg,
    String curso
) {
}
