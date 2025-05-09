package com.lab.studentmoney_gateway.dtos.address;

public record AddressCreateResponseDTO(
        Long id,
        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        String state,
        String cep
) {
}
