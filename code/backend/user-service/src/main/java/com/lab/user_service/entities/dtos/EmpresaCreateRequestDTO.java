package com.lab.user_service.entities.dtos;

public record EmpresaCreateRequestDTO(
        UsuarioCreateRequestDTO usuario,
        String cnpj
) {
}
