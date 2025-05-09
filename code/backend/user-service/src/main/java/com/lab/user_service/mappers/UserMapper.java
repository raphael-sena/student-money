package com.lab.user_service.mappers;

import com.lab.user_service.entities.User;
import com.lab.user_service.entities.dtos.users.UserCreateResponseDTO;
import com.lab.user_service.entities.dtos.users.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserCreateResponseDTO dto);
    UserCreateResponseDTO toDto(User entity);

    UserDTO toUserDTO(User user);
}
