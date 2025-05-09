package com.lab.control_service.entities.dtos.perk;

public record PerkDTO(
        Long id,
        String name,
        String description,
        String imageUrl,
        Double price,
        Long companyId
) {
}
