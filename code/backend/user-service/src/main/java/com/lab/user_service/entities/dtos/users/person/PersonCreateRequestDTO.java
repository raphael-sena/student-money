package com.lab.user_service.entities.dtos.users.person;

import com.lab.user_service.entities.dtos.users.UserCreateRequestDTO;

public record PersonCreateRequestDTO(
        UserCreateRequestDTO user,
        String cpf
//        InstituicaoEnsino instituicaoEnsino
) {
}
