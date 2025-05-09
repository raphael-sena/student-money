package com.lab.studentmoney_gateway.dtos.users;

import com.lab.studentmoney_gateway.dtos.address.AddressCreateRequestDTO;

public record UserCreateRequestDTO(
        String name,
        String email,
        String username,
        String password,
        AddressCreateRequestDTO address,
        String role
) {
}
