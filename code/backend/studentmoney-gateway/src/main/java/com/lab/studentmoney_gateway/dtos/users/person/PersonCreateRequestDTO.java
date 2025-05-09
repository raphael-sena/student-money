package com.lab.studentmoney_gateway.dtos.users.person;


import com.lab.studentmoney_gateway.dtos.users.UserCreateRequestDTO;

public record PersonCreateRequestDTO(
        UserCreateRequestDTO user,
        String cpf
//        InstituicaoEnsino instituicaoEnsino
) {
}
