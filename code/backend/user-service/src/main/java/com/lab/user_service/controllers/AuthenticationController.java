package com.lab.user_service.controllers;

import com.lab.user_service.entities.Student;
import com.lab.user_service.entities.User;
import com.lab.user_service.entities.dtos.users.AuthenticationDTO;
import com.lab.user_service.entities.dtos.users.LoginResponseDTO;
import com.lab.user_service.entities.dtos.users.person.student.StudentCreateRequestDTO;
import com.lab.user_service.infra.security.TokenService;
import com.lab.user_service.mappers.StudentMapper;
import com.lab.user_service.repositories.UserRepository;
import com.lab.user_service.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository repository;
    private final TokenService tokenService;
    private final UserService userService;
    private final StudentMapper studentMapper;


    public AuthenticationController(@Autowired AuthenticationManager authenticationManager,
                                    @Autowired UserRepository repository,
                                    @Autowired TokenService tokenService, UserService userService, StudentMapper studentMapper) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.tokenService = tokenService;
        this.userService = userService;
        this.studentMapper = studentMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @GetMapping("/test")
    public String test() {
        return "ok";
    }

    @PostMapping("/register/student")
    public ResponseEntity<Void> registerStudent(@RequestBody @Valid StudentCreateRequestDTO data){
        if(this.repository.findByUsername(data.person().user().email()) != null) return ResponseEntity.badRequest().build();

        Student newStudent = studentMapper.fromStudentCreatetoEntity(userService.createStudent(data));

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newStudent.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
