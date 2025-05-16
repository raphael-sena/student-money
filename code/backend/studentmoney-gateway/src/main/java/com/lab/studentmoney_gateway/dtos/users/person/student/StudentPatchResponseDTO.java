package com.lab.studentmoney_gateway.dtos.users.person.student;


import com.lab.studentmoney_gateway.dtos.users.person.PersonPatchResponseDTO;

public record StudentPatchResponseDTO(
        PersonPatchResponseDTO person,
        String course
) {
}
