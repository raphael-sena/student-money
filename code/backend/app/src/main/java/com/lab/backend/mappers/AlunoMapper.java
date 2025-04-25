package com.lab.backend.mappers;

import com.lab.backend.entities.Aluno;
import com.lab.backend.entities.dtos.AlunoCreateRequestDTO;
import com.lab.backend.entities.dtos.AlunoResponseDTO;
import com.lab.backend.entities.dtos.PessoaResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {PessoaMapper.class, UsuarioMapper.class})
public interface AlunoMapper {
    AlunoMapper INSTANCE = Mappers.getMapper(AlunoMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cpf", source = "pessoa.cpf")
    @Mapping(target = "rg", source = "rg")
    @Mapping(target = "curso", source = "curso")
    @Mapping(target = "nome", source = "pessoa.usuario.nome")
    @Mapping(target = "login", source = "pessoa.usuario.login")
    @Mapping(target = "senha", source = "pessoa.usuario.senha")
    @Mapping(target = "endereco", source = "pessoa.usuario.endereco")
    Aluno toEntity(AlunoCreateRequestDTO dto);

    @Mapping(target = "pessoa", expression = "java(toPessoaResponseDTO(aluno))")
    AlunoResponseDTO toResponseDTO(Aluno aluno);

    default PessoaResponseDTO toPessoaResponseDTO(Aluno aluno) {
        return new PessoaResponseDTO(
                UsuarioMapper.INSTANCE.toResponseDTO(aluno),
                aluno.getCpf()
        );
    }
}

