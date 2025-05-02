package com.lab.user_service.entities.dtos.users.person;

import com.lab.user_service.entities.dtos.users.UserCreateResponseDTO;

public record PersonCreateResponseDTO(
        UserCreateResponseDTO user,
        String cpf
//        InstituicaoEnsino instituicaoEnsino
) {
}
