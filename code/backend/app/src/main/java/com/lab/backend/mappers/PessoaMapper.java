package com.lab.backend.mappers;

import com.lab.backend.entities.Pessoa;
import com.lab.backend.entities.dtos.PessoaCreateRequestDTO;
import com.lab.backend.entities.dtos.PessoaResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UsuarioMapper.class})
public interface PessoaMapper {
    PessoaMapper INSTANCE = Mappers.getMapper(PessoaMapper.class);

    PessoaResponseDTO toResponseDTO(Pessoa pessoa);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nome", source = "usuario.nome")
    @Mapping(target = "login", source = "usuario.login")
    @Mapping(target = "senha", source = "usuario.senha")
    @Mapping(target = "endereco", source = "usuario.endereco")
    @Mapping(target = "cpf", source = "cpf")
    Pessoa toEntity(PessoaCreateRequestDTO dto);
}

