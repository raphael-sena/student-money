package com.lab.studentmoney_gateway.dtos.users.company;


import com.lab.studentmoney_gateway.dtos.users.UserCreateRequestDTO;

public record CompanyCreateRequestDTO(
        UserCreateRequestDTO user,
        String cnpj
) {
}
