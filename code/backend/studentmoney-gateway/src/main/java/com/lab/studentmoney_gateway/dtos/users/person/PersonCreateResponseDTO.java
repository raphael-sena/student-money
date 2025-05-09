package com.lab.studentmoney_gateway.dtos.users.person;


import com.lab.studentmoney_gateway.dtos.users.UserCreateResponseDTO;

public record PersonCreateResponseDTO(
        UserCreateResponseDTO user,
        String cpf
//        InstituicaoEnsino instituicaoEnsino
) {
}
