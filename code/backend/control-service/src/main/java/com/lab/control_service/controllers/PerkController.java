package com.lab.control_service.controllers;

import com.lab.control_service.entities.Perk;
import com.lab.control_service.entities.dtos.perk.PerkCreateRequestDTO;
import com.lab.control_service.entities.dtos.perk.PerkDTO;
import com.lab.control_service.services.PerkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/controls/perks")
public class PerkController {

    private final PerkService perkService;

    public PerkController(PerkService perkService) {
        this.perkService = perkService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerkDTO> findById(@PathVariable Long id) {
        Perk perk = perkService.findById(id);
        PerkDTO perkDTO = new PerkDTO(
                perk.getId(),
                perk.getName(),
                perk.getDescription(),
                perk.getImageUrl(),
                perk.getPrice(),
                perk.getCompanyId()
        );
        return ResponseEntity.ok(perkDTO);
    }

    @GetMapping
    public ResponseEntity<List<PerkDTO>> findAll() {
        List<PerkDTO> perks = perkService.findAll().stream()
                .map(perk -> new PerkDTO(
                        perk.getId(),
                        perk.getName(),
                        perk.getDescription(),
                        perk.getImageUrl(),
                        perk.getPrice(),
                        perk.getCompanyId()
                ))
                .toList();
        return ResponseEntity.ok(perks);
    }

    @PostMapping
    public ResponseEntity<PerkDTO> createPerk(@RequestBody PerkCreateRequestDTO perkDTO) {
        PerkDTO perk = perkService.createPerk(perkDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(perk.id())
                .toUri();

        return ResponseEntity.created(uri).body(perk);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerk(@PathVariable Long id) {
        perkService.deletePerk(id);
        return ResponseEntity.noContent().build();
    }
}
