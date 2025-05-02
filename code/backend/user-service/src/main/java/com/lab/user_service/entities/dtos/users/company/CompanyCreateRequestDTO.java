package com.lab.user_service.entities.dtos.users.company;

import com.lab.user_service.entities.dtos.users.UserCreateRequestDTO;

public record CompanyCreateRequestDTO(
        UserCreateRequestDTO user,
        String cnpj
) {
}
