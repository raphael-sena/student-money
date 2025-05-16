package com.lab.user_service.entities.dtos.users.person.student;

import com.lab.user_service.entities.dtos.users.person.PersonPatchRequestDTO;

public record StudentPatchRequestDTO(
        PersonPatchRequestDTO person,
        String course
) {
}
