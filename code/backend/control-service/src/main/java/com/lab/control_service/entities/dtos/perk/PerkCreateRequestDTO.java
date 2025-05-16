package com.lab.control_service.entities.dtos.perk;

public record PerkCreateRequestDTO(
        String name,
        String description,
        String imageUrl,
        Double price,
        Long companyId
) {
}
