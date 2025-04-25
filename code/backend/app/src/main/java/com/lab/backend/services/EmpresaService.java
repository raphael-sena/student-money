package com.lab.backend.services;

import com.lab.backend.entities.Aluno;
import com.lab.backend.entities.Empresa;
import com.lab.backend.entities.Endereco;
import com.lab.backend.entities.dtos.AlunoCreateRequestDTO;
import com.lab.backend.entities.dtos.EmpresaCreateRequestDTO;
import com.lab.backend.entities.dtos.EmpresaResponseDTO;
import com.lab.backend.mappers.AlunoMapper;
import com.lab.backend.mappers.EmpresaMapper;
import com.lab.backend.mappers.EnderecoMapper;
import com.lab.backend.mappers.UsuarioMapper;
import com.lab.backend.repositories.EmpresaRepository;
import com.lab.backend.repositories.EnderecoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final EnderecoRepository enderecoRepository;

    public EmpresaService(EmpresaRepository empresaRepository, EnderecoRepository enderecoRepository) {
        this.empresaRepository = empresaRepository;
        this.enderecoRepository = enderecoRepository;
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
    public EmpresaResponseDTO update(Long id, EmpresaCreateRequestDTO obj) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        empresaRepository.findByLogin(obj.usuario().login())
                .filter(a -> !a.getId().equals(id))
                .ifPresent(a -> {
                    throw new RuntimeException("Login já existe");
                });

        updateEmpresaFromDto(obj, empresa);
        empresaRepository.save(empresa);
        return EmpresaMapper.INSTANCE.toResponseDTO(empresa);
    }


    private void updateEmpresaFromDto(EmpresaCreateRequestDTO dto, Empresa empresa) {
        var usuarioDto = dto.usuario();
        if (usuarioDto != null) {
            if (usuarioDto.nome() != null) {
                empresa.setNome(usuarioDto.nome());
            }
            if (usuarioDto.login() != null) {
                empresa.setLogin(usuarioDto.login());
            }
            if (usuarioDto.senha() != null) {
                empresa.setSenha(usuarioDto.senha());
            }
            var enderecoDto = usuarioDto.endereco();
            if (enderecoDto != null) {
                if (empresa.getEndereco() == null) {
                    Endereco novoEndereco = EnderecoMapper.INSTANCE.toEntity(enderecoDto);
                    novoEndereco.setUsuario(empresa);
                    empresa.setEndereco(novoEndereco);
                } else {
                    EnderecoMapper.INSTANCE.updateFromDto(enderecoDto, empresa.getEndereco());
                }
                enderecoRepository.save(empresa.getEndereco());
            }
        }

        if (dto.cnpj() != null) {
            empresa.setCnpj(dto.cnpj());
        }
    }


    @Transactional
    public void delete(Long id) {
        findById(id);
        empresaRepository.deleteById(id);
    }


}
