package com.lab.user_service.mappers;

import com.lab.user_service.entities.Company;
import com.lab.user_service.entities.dtos.users.company.CompanyCreateResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    Company fromCompanyCreatetoEntity(CompanyCreateResponseDTO companyCreateResponseDTO);
}
