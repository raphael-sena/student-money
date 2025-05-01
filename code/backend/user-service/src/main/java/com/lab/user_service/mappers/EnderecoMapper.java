package com.lab.user_service.mappers;

import com.lab.user_service.entities.Endereco;
import com.lab.user_service.entities.dtos.EnderecoDTO;
import com.lab.user_service.entities.dtos.EnderecoResponseDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EnderecoMapper {

    EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);

    EnderecoResponseDTO toResponseDTO(Endereco endereco);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true) // será setado manualmente no service
    Endereco toEntity(EnderecoDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(EnderecoDTO dto, @MappingTarget Endereco entity);
}

