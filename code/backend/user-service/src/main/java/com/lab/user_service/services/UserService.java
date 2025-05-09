package com.lab.user_service.services;

import com.lab.user_service.entities.Company;
import com.lab.user_service.entities.Student;
import com.lab.user_service.entities.User;
import com.lab.user_service.entities.dtos.users.company.CompanyCreateRequestDTO;
import com.lab.user_service.entities.dtos.users.company.CompanyCreateResponseDTO;
import com.lab.user_service.entities.dtos.users.person.PersonCreateResponseDTO;
import com.lab.user_service.entities.dtos.users.person.PersonPatchResponseDTO;
import com.lab.user_service.entities.dtos.users.person.student.StudentCreateRequestDTO;
import com.lab.user_service.entities.dtos.users.person.student.StudentCreateResponseDTO;
import com.lab.user_service.entities.dtos.users.person.student.StudentPatchRequestDTO;
import com.lab.user_service.entities.dtos.users.person.student.StudentPatchResponseDTO;
import com.lab.user_service.mappers.AddressMapper;
import com.lab.user_service.mappers.UserMapper;
import com.lab.user_service.repositories.CompanyRepository;
import com.lab.user_service.repositories.StudentRespository;
import com.lab.user_service.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final StudentRespository studentRepository;
    private final UserFactory userFactory;
    private final UserMapper userMapper;
    private final CompanyRepository companyRepository;
    private final AddressMapper addressMapper;

    public UserService(UserRepository userRepository, StudentRespository studentRepository, UserFactory userFactory, UserMapper userMapper, CompanyRepository companyRepository, AddressMapper addressMapper) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.userFactory = userFactory;
        this.userMapper = userMapper;
        this.companyRepository = companyRepository;
        this.addressMapper = addressMapper;
    }

    @Transactional(readOnly = true)
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional(readOnly = true)
    public Student findStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Transactional
    public StudentCreateResponseDTO createStudent(StudentCreateRequestDTO obj) {

        System.err.println("entrou no createStudent");

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

    @Transactional
    public StudentPatchResponseDTO updateStudent(Long id, StudentPatchRequestDTO obj) {
        Student student = findStudentById(id);

        if(obj.person() != null) {
            if (obj.person().name() != null) {
                student.getUser().setName(obj.person().name());
            }

            if (obj.person().address() != null) {
                student.getUser().setAddress(
                        addressMapper.fromPatchtoEntity(obj.person().address())
                );
            }
        }

        if (obj.course() != null) {
            student.setCurso(obj.course());
        }

        studentRepository.save(student);

        return new StudentPatchResponseDTO(
                new PersonPatchResponseDTO(
                        student.getUser().getName(),
                        addressMapper.toPatchResponseDTO(student.getUser().getAddress())
                ),
                student.getCurso()
        );
    }

    @Transactional
    public CompanyCreateResponseDTO createCompany(CompanyCreateRequestDTO obj) {

        if (userRepository.existsByUsernameOrEmail(obj.user().username(), obj.user().email())) {
            throw new RuntimeException("Username or email already exists");
        }

        User user = userMapper.toEntity(userFactory.create(obj.user()));

        Company company = new Company();
        company.setCnpj(obj.cnpj());
        company.setUser(user);

        companyRepository.save(company);

        return new CompanyCreateResponseDTO(
                userMapper.toDto(user),
                obj.cnpj()
        );

    }

    public boolean userExists(String email) {
        return userRepository.existsByEmail(email);
    }
}
