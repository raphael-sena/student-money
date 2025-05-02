package com.lab.user_service.entities.dtos.users;

import com.lab.user_service.entities.dtos.address.AddressCreateRequestDTO;
import com.lab.user_service.entities.enums.Role;

public record UserCreateRequestDTO(
        String name,
        String email,
        String username,
        String password,
        AddressCreateRequestDTO address,
        Role role
) {
}
