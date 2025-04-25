package com.lab.backend.services;

import com.lab.backend.entities.Empresa;
import com.lab.backend.entities.dtos.EmpresaCreateRequestDTO;
import com.lab.backend.entities.dtos.EmpresaResponseDTO;
import com.lab.backend.mappers.EmpresaMapper;
import com.lab.backend.repositories.EmpresaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;


    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @Transactional(readOnly = true)
    public EmpresaResponseDTO findById(Long id) {
        return empresaRepository.findById(id)
                .map(EmpresaMapper.INSTANCE::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
    }

    @Transactional(readOnly = true)
    public List<EmpresaResponseDTO> findAll() {
        return empresaRepository.findAll()
                .stream()
                .map(EmpresaMapper.INSTANCE::toResponseDTO)
                .toList();
    }

    @Transactional
    public EmpresaResponseDTO create(EmpresaCreateRequestDTO obj) {

        if (obj.usuario() == null || obj.usuario().login() == null) {
            throw new RuntimeException("Usuário ou login não informado");
        }

        empresaRepository.findByLogin(obj.usuario().login())
                .ifPresent(empresa -> {
                    throw new RuntimeException("Usuário já existe");
                });

        Empresa empresa = EmpresaMapper.INSTANCE.toEntity(obj);
        empresaRepository.save(empresa);

        return EmpresaMapper.INSTANCE.toResponseDTO(empresa);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        empresaRepository.deleteById(id);
    }


}
