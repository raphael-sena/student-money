package com.lab.backend.entities.dtos;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String login,
        String senha,
        EnderecoResponseDTO endereco
) {
}
