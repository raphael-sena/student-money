package com.lab.studentmoney_gateway.dtos.users.person;


import com.lab.studentmoney_gateway.dtos.address.AddressPatchResponseDTO;

public record PersonPatchResponseDTO(
        String name,
        AddressPatchResponseDTO address
) {
}
