package com.lab.user_service.entities.dtos;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String login,
        String senha,
        EnderecoResponseDTO endereco,
        RoleDTO role
) {
}
