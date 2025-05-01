package com.lab.user_service.entities.dtos;

public record AlunoCreateRequestDTO(
        PessoaCreateRequestDTO pessoa,
        String rg,
        String curso
) {
}
