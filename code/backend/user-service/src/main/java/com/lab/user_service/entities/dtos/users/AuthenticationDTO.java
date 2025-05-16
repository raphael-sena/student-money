package com.lab.user_service.entities.dtos.users;

public record AuthenticationDTO(
        String email,
        String password
) {
}
