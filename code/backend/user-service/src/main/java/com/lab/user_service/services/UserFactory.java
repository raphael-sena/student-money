package com.lab.user_service.services;

import com.lab.user_service.entities.Address;
import com.lab.user_service.entities.User;
import com.lab.user_service.entities.dtos.users.UserCreateRequestDTO;
import com.lab.user_service.entities.dtos.users.UserCreateResponseDTO;
import com.lab.user_service.entities.enums.Role;
import com.lab.user_service.mappers.UserMapper;
import com.lab.user_service.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserFactory {

    private final AddressService addressService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserFactory(AddressService addressService, UserRepository userRepository, UserMapper userMapper) {
        this.addressService = addressService;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
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

        return userMapper.toDto(user);
    }
}
