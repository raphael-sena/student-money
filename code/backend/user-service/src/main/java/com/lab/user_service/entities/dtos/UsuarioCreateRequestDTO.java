package com.lab.user_service.entities.dtos;

public record UsuarioCreateRequestDTO(
        String nome,
        String login,
        String senha,
        EnderecoDTO endereco
) {
}
