package com.lab.user_service.entities.dtos.users.person.student;

import com.lab.user_service.entities.dtos.users.person.PersonPatchResponseDTO;

public record StudentPatchResponseDTO(
        PersonPatchResponseDTO person,
        String course
) {
}
