package com.lab.backend.entities.dtos;

public record EmpresaResponseDTO(
        UsuarioResponseDTO usuario,
        String cnpj
) {
}
