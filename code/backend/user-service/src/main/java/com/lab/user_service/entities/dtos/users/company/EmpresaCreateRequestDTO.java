package com.lab.user_service.entities.dtos.users.company;

import com.lab.user_service.entities.dtos.users.UserCreateRequestDTO;

public record EmpresaCreateRequestDTO(
        UserCreateRequestDTO usuario,
        String cnpj
) {
}
