package com.lab.control_service.services;

import com.lab.control_service.clients.UserClient;
import com.lab.control_service.entities.Perk;
import com.lab.control_service.entities.dtos.perk.PerkCreateRequestDTO;
import com.lab.control_service.entities.dtos.perk.PerkDTO;
import com.lab.control_service.entities.dtos.perk.PerkPatchRequestDTO;
import com.lab.control_service.repositories.PerkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PerkService {

    private final PerkRepository perkRepository;
    private final UserClient userClient;

    public PerkService(PerkRepository perkRepository, UserClient userClient) {
        this.perkRepository = perkRepository;
        this.userClient = userClient;
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

        companyExists(obj.companyId());

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

    @Transactional
    public PerkDTO updatePerk(PerkPatchRequestDTO obj, Long id) {
        Perk perk = findById(id);

        companyExists(obj.companyId());

        if (obj.name() != null) {
            perk.setName(obj.name());
        }

        if (obj.description() != null) {
            perk.setDescription(obj.description());
        }

        if (obj.imageUrl() != null) {
            perk.setImageUrl(obj.imageUrl());
        }

        if (obj.price() != null) {
            perk.setPrice(obj.price());
        }

        Perk updatedPerk = perkRepository.save(perk);

        return new PerkDTO(
                updatedPerk.getId(),
                updatedPerk.getName(),
                updatedPerk.getDescription(),
                updatedPerk.getImageUrl(),
                updatedPerk.getPrice(),
                updatedPerk.getCompanyId()
        );
    }

    private void companyExists(Long obj) {
        boolean companyExists = userClient.companyExists(obj);
        if (!companyExists) {
            throw new IllegalArgumentException("Company with id " + obj + " does not exist.");
        }
    }
}
