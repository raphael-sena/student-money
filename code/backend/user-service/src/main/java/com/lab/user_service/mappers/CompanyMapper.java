package com.lab.user_service.mappers;

import com.lab.user_service.entities.Company;
import com.lab.user_service.entities.dtos.users.company.CompanyCreateResponseDTO;
import com.lab.user_service.entities.dtos.users.company.CompanyDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    @Mapping(source = "user.name", target = "name")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.address", target = "address")
    @Mapping(source = "user.role", target = "role")
    CompanyDTO toDTO(Company company);
}
