package com.lab.user_service.entities.dtos;

public record PessoaCreateRequestDTO(
        UsuarioCreateRequestDTO usuario,
        String cpf
//        InstituicaoEnsino instituicaoEnsino
) {
}
