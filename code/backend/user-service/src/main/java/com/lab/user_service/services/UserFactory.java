package com.lab.user_service.services;

import com.lab.user_service.entities.Address;
import com.lab.user_service.entities.User;
import com.lab.user_service.entities.dtos.users.UserCreateRequestDTO;
import com.lab.user_service.entities.dtos.users.UserCreateResponseDTO;
import com.lab.user_service.entities.enums.Role;
import com.lab.user_service.mappers.AddressMapper;
import com.lab.user_service.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserFactory {

    private final AddressService addressService;
    private final AddressMapper addressMapper;
    private final UserRepository userRepository;

    public UserFactory(AddressService addressService, AddressMapper addressMapper, UserRepository userRepository) {
        this.addressService = addressService;
        this.addressMapper = addressMapper;
        this.userRepository = userRepository;
    }

    @Transactional
    public UserCreateResponseDTO create(UserCreateRequestDTO obj) {

        String encryptedPassword = new BCryptPasswordEncoder().encode(obj.password());
        User user = new User();

        Address address = addressService.createAddressEntity(obj.address());

        user.setName(obj.name());
        user.setEmail(obj.email());
        user.setUsername(obj.username());
        user.setPassword(encryptedPassword);

        switch (obj.role()) {
            case ADMIN -> {
                user.setRole(Role.ADMIN);
            }

            case STUDENT -> {
                user.setRole(Role.STUDENT);
            }

            case PROFESSOR -> {
                user.setRole(Role.PROFESSOR);
            }

            case COMPANY -> {
                user.setRole(Role.COMPANY);
            }

            default -> {
                throw new IllegalArgumentException("Invalid role: " + obj.role());
            }
        }

        userRepository.save(user);

        user.setAddress(address);

        userRepository.save(user);

        return new UserCreateResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                addressMapper.toDto(user.getAddress()),
                user.getRole()
        );
    }
}
