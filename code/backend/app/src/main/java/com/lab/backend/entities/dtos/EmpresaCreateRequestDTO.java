package com.lab.backend.entities.dtos;

public record EmpresaCreateRequestDTO(
        UsuarioCreateRequestDTO usuario,
        String cnpj
) {
}
