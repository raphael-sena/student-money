package com.lab.user_service.entities.dtos.users.person.student;

import com.lab.user_service.entities.dtos.users.person.PersonCreateResponseDTO;

public record StudentCreateResponseDTO(
    PersonCreateResponseDTO person,
    String rg,
    String course
) {
}
