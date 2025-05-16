package com.lab.control_service.services;

import com.lab.control_service.entities.Perk;
import com.lab.control_service.entities.dtos.perk.PerkCreateRequestDTO;
import com.lab.control_service.entities.dtos.perk.PerkDTO;
import com.lab.control_service.repositories.PerkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PerkService {

    private final PerkRepository perkRepository;

    public PerkService(PerkRepository perkRepository) {
        this.perkRepository = perkRepository;
    }

    @Transactional(readOnly = true)
    public Perk findById(Long id) {
        return perkRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Perk not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Perk> findAll() {
        return perkRepository.findAll();
    }

    @Transactional
    public PerkDTO createPerk(PerkCreateRequestDTO obj) {

        Perk perk = new Perk();
        perk.setName(obj.name());
        perk.setDescription(obj.description());
        perk.setImageUrl(obj.imageUrl());
        perk.setPrice(obj.price());
        perk.setCompanyId(obj.companyId());

        Perk savedPerk = perkRepository.save(perk);

        return new PerkDTO(
                savedPerk.getId(),
                savedPerk.getName(),
                savedPerk.getDescription(),
                savedPerk.getImageUrl(),
                savedPerk.getPrice(),
                savedPerk.getCompanyId()
        );
    }

    @Transactional
    public void deletePerk(Long id) {
        findById(id);
        perkRepository.deleteById(id);
    }
}
