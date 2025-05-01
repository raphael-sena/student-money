package com.lab.user_service.entities.dtos;

public record EnderecoDTO(
        String rua,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep,
        UsuarioResponseDTO usuario
) {
}
