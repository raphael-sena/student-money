package com.lab.backend.entities.dtos;

public record EnderecoResponseDTO(
        Long id,
        String rua,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep
) {
}
