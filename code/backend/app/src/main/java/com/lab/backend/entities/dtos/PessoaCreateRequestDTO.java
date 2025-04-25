package com.lab.backend.entities.dtos;

public record PessoaCreateRequestDTO(
        UsuarioCreateRequestDTO usuario,
        String cpf
//        InstituicaoEnsino instituicaoEnsino
) {
}
