package com.lab.studentmoney_gateway.dtos.address;

public record AddressCreateRequestDTO(
        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        String state,
        String cep
) {
}
