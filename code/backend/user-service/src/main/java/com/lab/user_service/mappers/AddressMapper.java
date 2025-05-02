package com.lab.user_service.mappers;

import com.lab.user_service.entities.Address;
import com.lab.user_service.entities.dtos.address.AddressCreateResponseDTO;
import com.lab.user_service.entities.dtos.address.AddressPatchRequestDTO;
import com.lab.user_service.entities.dtos.address.AddressPatchResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressCreateResponseDTO toDto(Address address);
    AddressPatchRequestDTO toPatchRequestDTO(Address address);
    AddressPatchResponseDTO toPatchResponseDTO(Address address);
    Address toEntity(AddressCreateResponseDTO addressDTO);
    Address fromPatchtoEntity(AddressPatchRequestDTO addressDTO);
}
