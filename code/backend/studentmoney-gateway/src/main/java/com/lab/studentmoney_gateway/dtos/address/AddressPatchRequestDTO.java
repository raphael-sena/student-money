package com.lab.studentmoney_gateway.dtos.address;

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
