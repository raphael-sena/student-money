package com.lab.user_service.controllers;

import com.lab.user_service.entities.dtos.users.person.student.StudentCreateRequestDTO;
import com.lab.user_service.entities.dtos.users.person.student.StudentCreateResponseDTO;
import com.lab.user_service.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/students")
    public ResponseEntity<StudentCreateResponseDTO> createStudent(StudentCreateRequestDTO obj) {
        StudentCreateResponseDTO student = userService.createStudent(obj);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(student.person().user().id())
                .toUri();

        return ResponseEntity.created(uri).body(student);
    }
}
