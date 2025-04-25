package com.lab.backend.services;

import com.lab.backend.entities.Empresa;
import com.lab.backend.entities.dtos.EmpresaCreateRequestDTO;
import com.lab.backend.entities.dtos.EmpresaResponseDTO;
import com.lab.backend.mappers.EmpresaMapper;
import com.lab.backend.repositories.EmpresaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;


    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
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

}
