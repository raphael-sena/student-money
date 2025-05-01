package com.lab.user_service.mappers;

import com.lab.user_service.entities.Pessoa;
import com.lab.user_service.entities.dtos.PessoaCreateRequestDTO;
import com.lab.user_service.entities.dtos.PessoaResponseDTO;
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
    @Mapping(target = "role", source = "usuario.role")
    @Mapping(target = "cpf", source = "cpf")
    Pessoa toEntity(PessoaCreateRequestDTO dto);
}

