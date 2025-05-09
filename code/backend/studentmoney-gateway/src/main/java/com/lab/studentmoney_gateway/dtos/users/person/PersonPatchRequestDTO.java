package com.lab.studentmoney_gateway.dtos.users.person;


import com.lab.studentmoney_gateway.dtos.address.AddressPatchRequestDTO;

public record PersonPatchRequestDTO(
        String name,
        AddressPatchRequestDTO address
//        InstituicaoEnsino instituicaoEnsino
) {
}
