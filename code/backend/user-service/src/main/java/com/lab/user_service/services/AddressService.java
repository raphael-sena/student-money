package com.lab.user_service.services;

import com.lab.user_service.entities.Address;
import com.lab.user_service.entities.dtos.address.AddressCreateRequestDTO;
import com.lab.user_service.repositories.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional
    public Address createAddressEntity(AddressCreateRequestDTO dto) {
        Address address = new Address();
        address.setStreet(dto.street());
        address.setNumber(dto.number());
        address.setComplement(dto.complement());
        address.setNeighbourhood(dto.neighborhood());
        address.setCity(dto.city());
        address.setState(dto.state());
        address.setCep(dto.cep());

        return addressRepository.save(address);
    }

}
