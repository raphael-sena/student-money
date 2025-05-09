package com.lab.studentmoney_gateway.controllers;

import com.lab.studentmoney_gateway.clients.UserClient;
import com.lab.studentmoney_gateway.dtos.users.AuthenticationDTO;
import com.lab.studentmoney_gateway.dtos.users.LoginResponseDTO;
import com.lab.studentmoney_gateway.dtos.users.UserDTO;
import com.lab.studentmoney_gateway.dtos.users.company.CompanyCreateRequestDTO;
import com.lab.studentmoney_gateway.dtos.users.person.student.StudentCreateRequestDTO;
import com.lab.studentmoney_gateway.producers.UserProducer;
import com.lab.studentmoney_gateway.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final TokenService tokenService;
    private final UserClient userClient;
    private final UserProducer userProducer;

    @Autowired
    public AuthenticationController(TokenService tokenService, UserClient userClient, UserProducer userProducer) {
        this.tokenService = tokenService;
        this.userClient = userClient;
        this.userProducer = userProducer;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data){
        UserDTO user = userClient.validateCredentials(data);
        String token = tokenService.generateToken(user);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register/student")
    public ResponseEntity<Void> registerStudent(@RequestBody @Valid StudentCreateRequestDTO data){
        try {
            if(userClient.userExists(data.person().user().email())) return ResponseEntity.badRequest().build();

            userProducer.sendStudentCreation(data);
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            e.printStackTrace(); // ou log
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/register/company")
    public ResponseEntity<Void> registerCompany(@RequestBody @Valid CompanyCreateRequestDTO data){
        try {
            if(userClient.userExists(data.user().email())) return ResponseEntity.badRequest().build();

            userProducer.sendCompanyCreation(data);
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            e.printStackTrace(); // ou log
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
