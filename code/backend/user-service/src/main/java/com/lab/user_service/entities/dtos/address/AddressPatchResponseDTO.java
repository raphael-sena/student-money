package com.lab.user_service.entities.dtos.address;

public record AddressPatchResponseDTO(
        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        String state,
        String cep
) {
}
