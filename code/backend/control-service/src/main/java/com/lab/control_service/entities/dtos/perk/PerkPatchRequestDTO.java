package com.lab.control_service.entities.dtos.perk;

import jakarta.validation.constraints.NotNull;

public record PerkPatchRequestDTO(
        String name,
        String description,
        String imageUrl,
        Double price,

        @NotNull(message = "Company ID cannot be null")
        Long companyId
) {
}
