package com.lab.backend.entities.dtos;

public record UsuarioCreateRequestDTO(
        String nome,
        String login,
        String senha,
        EnderecoDTO endereco
) {
}
