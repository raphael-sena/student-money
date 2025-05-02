package com.lab.user_service.entities.dtos.users.person;

import com.lab.user_service.entities.dtos.address.AddressPatchResponseDTO;

public record PersonPatchResponseDTO(
        String name,
        AddressPatchResponseDTO address
) {
}
