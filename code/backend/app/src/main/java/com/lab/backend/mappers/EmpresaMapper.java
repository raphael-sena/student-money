package com.lab.backend.mappers;

import com.lab.backend.entities.Empresa;
import com.lab.backend.entities.Pessoa;
import com.lab.backend.entities.dtos.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UsuarioMapper.class, EnderecoMapper.class})
public interface EmpresaMapper {
    EmpresaMapper INSTANCE = Mappers.getMapper(EmpresaMapper.class);

    @Mapping(target = "usuario", expression = "java(mapEmpresaToUsuario(empresa))")
    @Mapping(target = "cnpj", source = "cnpj")
    EmpresaResponseDTO toResponseDTO(Empresa empresa);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nome", source = "usuario.nome")
    @Mapping(target = "login", source = "usuario.login")
    @Mapping(target = "senha", source = "usuario.senha")
    @Mapping(target = "endereco", source = "usuario.endereco")
    @Mapping(target = "cnpj", source = "cnpj")
    Empresa toEntity(EmpresaCreateRequestDTO dto);

    default UsuarioResponseDTO mapEmpresaToUsuario(Empresa empresa) {
        if (empresa == null) {
            return null;
        }
        return new UsuarioResponseDTO(
                empresa.getId(),
                empresa.getNome(),
                empresa.getLogin(),
                empresa.getSenha(),
                empresa.getEndereco() != null ? EnderecoMapper.INSTANCE.toResponseDTO(empresa.getEndereco()) : null
        );
    }
}


