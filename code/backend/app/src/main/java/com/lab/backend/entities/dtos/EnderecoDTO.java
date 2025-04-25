package com.lab.backend.entities.dtos;

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
