package com.lab.user_service.services;

import com.lab.user_service.entities.Student;
import com.lab.user_service.entities.User;
import com.lab.user_service.entities.dtos.address.AddressCreateResponseDTO;
import com.lab.user_service.entities.dtos.users.UserCreateResponseDTO;
import com.lab.user_service.entities.dtos.users.person.PersonCreateResponseDTO;
import com.lab.user_service.entities.dtos.users.person.student.StudentCreateRequestDTO;
import com.lab.user_service.entities.dtos.users.person.student.StudentCreateResponseDTO;
import com.lab.user_service.mappers.AddressMapper;
import com.lab.user_service.mappers.UserMapper;
import com.lab.user_service.repositories.StudentRespository;
import com.lab.user_service.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final StudentRespository studentRepository;
    private final UserFactory userFactory;
    private final UserMapper userMapper;
    private final AddressMapper addressMapper;

    public UserService(UserRepository userRepository, StudentRespository studentRepository, UserFactory userFactory, UserMapper userMapper, AddressMapper addressMapper) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.userFactory = userFactory;
        this.userMapper = userMapper;
        this.addressMapper = addressMapper;
    }

    @Transactional(readOnly = true)
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public StudentCreateResponseDTO createStudent(StudentCreateRequestDTO obj) {

        if (userRepository.existsByUsernameOrEmail(obj.person().user().username(), obj.person().user().email())) {
            throw new RuntimeException("Username or email already exists");
        }

        User user = userMapper.toEntity(userFactory.create(obj.person().user()));

        Student student = new Student();
        student.setUser(user);
        student.setCurso(obj.course());
        student.setRg(obj.rg());
        student.setCpf(obj.person().cpf());

        studentRepository.save(student);

        return new StudentCreateResponseDTO(
                new PersonCreateResponseDTO(
                        userMapper.toDto(user),
                        obj.person().cpf()
                ),
                obj.rg(),
                obj.course()
        );
    }
}
