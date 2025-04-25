package com.lab.backend.mappers;

import com.lab.backend.entities.Endereco;
import com.lab.backend.entities.dtos.EnderecoDTO;
import com.lab.backend.entities.dtos.EnderecoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EnderecoMapper {

    EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);

    EnderecoResponseDTO toResponseDTO(Endereco endereco);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true) // será setado manualmente no service
    Endereco toEntity(EnderecoDTO dto);
}

