package com.lab.user_service.services;

import com.lab.user_service.entities.Student;
import com.lab.user_service.entities.User;
import com.lab.user_service.entities.dtos.address.AddressCreateResponseDTO;
import com.lab.user_service.entities.dtos.users.UserCreateResponseDTO;
import com.lab.user_service.entities.dtos.users.person.PersonCreateResponseDTO;
import com.lab.user_service.entities.dtos.users.person.student.StudentCreateRequestDTO;
import com.lab.user_service.entities.dtos.users.person.student.StudentCreateResponseDTO;
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

    public UserService(UserRepository userRepository, StudentRespository studentRepository, UserFactory userFactory, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.userFactory = userFactory;
        this.userMapper = userMapper;
    }

    @Transactional(readOnly = true)
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public StudentCreateResponseDTO createStudent(StudentCreateRequestDTO obj) {

        User user = userMapper.toEntity(userFactory.create(obj.person().user()));

        Student student = new Student();
        student.setUser(user);
        student.setCurso(obj.course());
        student.setRg(obj.rg());
        student.setCpf(obj.person().cpf());

        studentRepository.save(student);

        return new StudentCreateResponseDTO(
                new PersonCreateResponseDTO(
                        new UserCreateResponseDTO(
                                user.getId(),
                                user.getName(),
                                user.getEmail(),
                                user.getUsername(),
                                user.getPassword(),
                                new AddressCreateResponseDTO(
                                        user.getAddress().getId(),
                                        user.getAddress().getStreet(),
                                        user.getAddress().getNumber(),
                                        user.getAddress().getComplement(),
                                        user.getAddress().getNeighbourhood(),
                                        user.getAddress().getCity(),
                                        user.getAddress().getState(),
                                        user.getAddress().getCep()
                                        ),
                                user.getRole()
                        ),
                        obj.person().cpf()
                ),
                obj.rg(),
                obj.course()
        );
    }
}
