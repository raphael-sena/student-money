package com.lab.studentmoney_gateway.dtos.users;


import com.lab.studentmoney_gateway.dtos.address.AddressCreateResponseDTO;

public record UserCreateResponseDTO(
        Long id,
        String name,
        String email,
        String username,
        String password,
        AddressCreateResponseDTO address,
        String role
) {
}
