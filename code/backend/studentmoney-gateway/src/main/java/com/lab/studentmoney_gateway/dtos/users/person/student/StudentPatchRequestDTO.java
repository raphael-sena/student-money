package com.lab.studentmoney_gateway.dtos.users.person.student;


import com.lab.studentmoney_gateway.dtos.users.person.PersonPatchRequestDTO;

public record StudentPatchRequestDTO(
        PersonPatchRequestDTO person,
        String course
) {
}
