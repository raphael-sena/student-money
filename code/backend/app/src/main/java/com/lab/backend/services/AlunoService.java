package com.lab.backend.services;

import com.lab.backend.entities.Aluno;
import com.lab.backend.entities.Endereco;
import com.lab.backend.entities.dtos.*;
import com.lab.backend.mappers.AlunoMapper;
import com.lab.backend.mappers.EnderecoMapper;
import com.lab.backend.repositories.EnderecoRepository;
import org.springframework.stereotype.Service;
import com.lab.backend.repositories.AlunoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
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

        Endereco endereco = EnderecoMapper.INSTANCE.toEntity(obj.pessoa().usuario().endereco());
        endereco.setUsuario(aluno);
        enderecoRepository.save(endereco);

        aluno.setEndereco(endereco);

        aluno.setCpf(obj.pessoa().cpf());

        aluno.setRg(obj.rg());
        aluno.setCurso(obj.curso());

        return AlunoMapper.INSTANCE.toResponseDTO(aluno);
    }
}
