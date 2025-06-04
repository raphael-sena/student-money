package com.lab.user_service.entities.dtos.users.company;

import com.lab.user_service.entities.dtos.address.AddressCreateResponseDTO;
import com.lab.user_service.entities.enums.Role;

public record CompanyDTO(
        Long id,
        String name,
        String email,
        String username,
        AddressCreateResponseDTO address,
        String cnpj,
        Role role
) {
}
