package com.lab.user_service.mappers;

import com.lab.user_service.entities.Student;
import com.lab.user_service.entities.dtos.users.person.student.StudentCreateResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student fromStudentCreatetoEntity(StudentCreateResponseDTO studentCreateResponseDTO);
}
