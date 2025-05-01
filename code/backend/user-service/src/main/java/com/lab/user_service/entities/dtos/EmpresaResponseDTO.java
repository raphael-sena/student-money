package com.lab.user_service.entities.dtos;

public record EmpresaResponseDTO(
        UsuarioResponseDTO usuario,
        String cnpj
) {
}
