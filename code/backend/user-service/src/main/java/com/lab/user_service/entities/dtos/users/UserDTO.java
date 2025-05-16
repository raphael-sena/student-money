package com.lab.user_service.entities.dtos.users;

public record UserDTO(
        String email,
        String id,
        String role
) {
}
