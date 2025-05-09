package com.lab.user_service.controllers;

import com.lab.user_service.entities.dtos.users.person.student.StudentPatchRequestDTO;
import com.lab.user_service.entities.dtos.users.person.student.StudentPatchResponseDTO;
import com.lab.user_service.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PatchMapping("/students/{id}")
    public ResponseEntity<StudentPatchResponseDTO> createStudent(@PathVariable Long id, @RequestBody @Valid StudentPatchRequestDTO obj) {
        StudentPatchResponseDTO student = userService.updateStudent(id, obj);
        return ResponseEntity.ok().body(student);
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> userExists(@RequestParam String email) {
        boolean exists = userService.userExists(email);
        return ResponseEntity.ok(exists);
    }
}
