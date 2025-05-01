package com.lab.user_service.entities.dtos;

public record PessoaResponseDTO(
        UsuarioResponseDTO usuario,
        String cpf
//        InstituicaoEnsino instituicaoEnsino
) {
}
