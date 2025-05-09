package com.lab.studentmoney_gateway.dtos.users.person.student;

import com.lab.studentmoney_gateway.dtos.users.person.PersonCreateRequestDTO;

public record StudentCreateRequestDTO(
        PersonCreateRequestDTO person,
        String rg,
        String course
) {
}
