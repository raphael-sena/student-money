package com.lab.user_service.entities.dtos.users.person.student;

import com.lab.user_service.entities.dtos.users.person.PersonCreateRequestDTO;

public record StudentCreateRequestDTO(
        PersonCreateRequestDTO person,
        String rg,
        String course
) {
}
