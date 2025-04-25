package com.lab.backend.services;

import com.lab.backend.entities.Aluno;
import com.lab.backend.entities.Endereco;
import com.lab.backend.entities.dtos.*;
import com.lab.backend.mappers.AlunoMapper;
import com.lab.backend.mappers.EnderecoMapper;
import com.lab.backend.mappers.UsuarioMapper;
import com.lab.backend.repositories.EnderecoRepository;
import org.springframework.stereotype.Service;
import com.lab.backend.repositories.AlunoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final EnderecoRepository enderecoRepository;


    public AlunoService(AlunoRepository alunoRepository, EnderecoRepository enderecoRepository) {
        this.alunoRepository = alunoRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional(readOnly = true)
    public Aluno findById(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

    @Transactional(readOnly = true)
    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    @Transactional
    public AlunoResponseDTO create(AlunoCreateRequestDTO obj) {

        alunoRepository.findByLogin(obj.pessoa().usuario().login())
                .ifPresent(aluno -> {
                    throw new RuntimeException("Login já existe");
                });

        Aluno aluno = AlunoMapper.INSTANCE.toEntity(obj);
        alunoRepository.save(aluno);

        return getAlunoMapper(obj, aluno);
    }

    private AlunoResponseDTO getAlunoMapper(AlunoCreateRequestDTO obj, Aluno aluno) {
        Endereco endereco = EnderecoMapper.INSTANCE.toEntity(obj.pessoa().usuario().endereco());
        endereco.setUsuario(aluno);
        enderecoRepository.save(endereco);

        aluno.setEndereco(endereco);

        aluno.setNome(obj.pessoa().usuario().nome());
        aluno.setLogin(obj.pessoa().usuario().login());
        aluno.setSenha(obj.pessoa().usuario().senha());

        aluno.setCpf(obj.pessoa().cpf());
        aluno.setRg(obj.rg());
        aluno.setCurso(obj.curso());

        alunoRepository.save(aluno);

        return AlunoMapper.INSTANCE.toResponseDTO(aluno);
    }

    @Transactional
    public AlunoResponseDTO update(Long id, AlunoCreateRequestDTO obj) {
        Aluno aluno = findById(id);

        alunoRepository.findByLogin(obj.pessoa().usuario().login())
                .filter(a -> !a.getId().equals(id))
                .ifPresent(a -> {
                    throw new RuntimeException("Login já existe");
                });

        updateAlunoFromDto(obj, aluno);
        alunoRepository.save(aluno);
        return AlunoMapper.INSTANCE.toResponseDTO(aluno);
    }

    private void updateAlunoFromDto(AlunoCreateRequestDTO dto, Aluno aluno) {
        var usuarioDto = dto.pessoa().usuario();
        var enderecoDto = usuarioDto.endereco();

        UsuarioMapper.INSTANCE.updateFromDto(usuarioDto, aluno);

        if (enderecoDto != null) {
            if (aluno.getEndereco() == null) {
                Endereco novoEndereco = EnderecoMapper.INSTANCE.toEntity(enderecoDto);
                novoEndereco.setUsuario(aluno);
                aluno.setEndereco(novoEndereco);
            } else {
                EnderecoMapper.INSTANCE.updateFromDto(enderecoDto, aluno.getEndereco());
            }
            enderecoRepository.save(aluno.getEndereco());
        }

        if (dto.pessoa().cpf() != null) {
            aluno.setCpf(dto.pessoa().cpf());
        }
        if (dto.rg() != null) {
            aluno.setRg(dto.rg());
        }
        if (dto.curso() != null) {
            aluno.setCurso(dto.curso());
        }
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        alunoRepository.deleteById(id);
    }
}
