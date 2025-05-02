package com.lab.user_service.mappers;

import com.lab.user_service.entities.Address;
import com.lab.user_service.entities.dtos.address.AddressCreateResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressCreateResponseDTO toDto(Address address);
    Address toEntity(AddressCreateResponseDTO addressDTO);
}
