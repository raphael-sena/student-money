package com.lab.user_service.entities.dtos.users.company;

import com.lab.user_service.entities.dtos.users.UserCreateResponseDTO;

public record EmpresaResponseDTO(
        UserCreateResponseDTO usuario,
        String cnpj
) {
}
