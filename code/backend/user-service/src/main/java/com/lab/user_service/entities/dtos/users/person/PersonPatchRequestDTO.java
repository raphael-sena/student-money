package com.lab.user_service.entities.dtos.users.person;

import com.lab.user_service.entities.dtos.address.AddressPatchRequestDTO;

public record PersonPatchRequestDTO(
        String name,
        AddressPatchRequestDTO address
//        InstituicaoEnsino instituicaoEnsino
) {
}
