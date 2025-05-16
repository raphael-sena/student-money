package com.lab.studentmoney_gateway.dtos.users;

public record AuthenticationDTO(
        String email,
        String password
) {
}
