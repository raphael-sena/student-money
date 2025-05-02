package com.lab.user_service.entities.dtos.address;

public record AddressPatchRequestDTO(
        String street,
        String number,
        String neighborhood,
        String city,
        String state,
        String country,
        String cep
) {
}
