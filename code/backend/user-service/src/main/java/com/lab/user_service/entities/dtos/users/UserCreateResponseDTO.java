package com.lab.user_service.entities.dtos.users;


import com.lab.user_service.entities.dtos.address.AddressCreateResponseDTO;
import com.lab.user_service.entities.enums.Role;

public record UserCreateResponseDTO(
        Long id,
        String name,
        String email,
        String username,
        String password,
        AddressCreateResponseDTO address,
        Role role
) {
}
