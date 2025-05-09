package com.lab.studentmoney_gateway.dtos.users.person.student;


import com.lab.studentmoney_gateway.dtos.users.person.PersonCreateResponseDTO;

public record StudentCreateResponseDTO(
    PersonCreateResponseDTO person,
    String rg,
    String course
) {
}
