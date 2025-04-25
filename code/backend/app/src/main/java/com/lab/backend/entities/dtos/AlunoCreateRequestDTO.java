package com.lab.backend.entities.dtos;

public record AlunoCreateRequestDTO(
        PessoaCreateRequestDTO pessoa,
        String rg,
        String curso
) {
}
