package com.lab.studentmoney_gateway.dtos.users.company;


import com.lab.studentmoney_gateway.dtos.users.UserCreateResponseDTO;

public record CompanyCreateResponseDTO(
        UserCreateResponseDTO user,
        String cnpj
) {
}
