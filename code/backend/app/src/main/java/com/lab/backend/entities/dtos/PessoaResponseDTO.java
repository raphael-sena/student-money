package com.lab.backend.entities.dtos;

public record PessoaResponseDTO(
        UsuarioResponseDTO usuario,
        String cpf
//        InstituicaoEnsino instituicaoEnsino
) {
}
