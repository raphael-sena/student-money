package com.lab.backend.mappers;

import com.lab.backend.entities.Usuario;
import com.lab.backend.entities.dtos.UsuarioCreateRequestDTO;
import com.lab.backend.entities.dtos.UsuarioResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {EnderecoMapper.class})
public interface UsuarioMapper {
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioResponseDTO toResponseDTO(Usuario usuario);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "endereco", source = "endereco")
    Usuario toEntity(UsuarioCreateRequestDTO dto);
}

