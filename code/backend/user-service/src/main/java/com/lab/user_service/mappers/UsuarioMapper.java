package com.lab.user_service.mappers;

import com.lab.user_service.entities.Usuario;
import com.lab.user_service.entities.dtos.UsuarioCreateRequestDTO;
import com.lab.user_service.entities.dtos.UsuarioResponseDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {EnderecoMapper.class})
public interface UsuarioMapper {
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioResponseDTO toResponseDTO(Usuario usuario);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "endereco", source = "endereco")
    @Mapping(target = "role", source = "role")
    Usuario toEntity(UsuarioCreateRequestDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(UsuarioCreateRequestDTO dto, @MappingTarget Usuario entity);
}