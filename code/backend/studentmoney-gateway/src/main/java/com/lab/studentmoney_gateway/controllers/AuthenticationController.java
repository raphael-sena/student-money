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
import reactor.core.publisher.Mono;

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
    public Mono<ResponseEntity<LoginResponseDTO>> login(@RequestBody @Valid AuthenticationDTO data){
        return Mono.fromCallable(() -> {
            UserDTO user = userClient.validateCredentials(data);
            String token = tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginResponseDTO(token));
        });
    }

    @PostMapping("/register/student")
    public Mono<ResponseEntity<String>> registerStudent(@RequestBody @Valid StudentCreateRequestDTO data) {
        return Mono.fromCallable(() -> {
            if (userClient.userExists(data.person().user().email())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já cadastrado");
            }

            userProducer.sendStudentCreation(data);
            return ResponseEntity.ok().body("Registro de estudante aceito.");
        }).onErrorResume(e -> {
            e.printStackTrace(); // log opcional
            return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar registro de estudante."));
        });
    }


    @PostMapping("/register/company")
    public Mono<ResponseEntity<String>> registerCompany(@RequestBody @Valid CompanyCreateRequestDTO data) {
        return Mono.fromCallable(() -> {
            if (userClient.userExists(data.user().email())) {
                return ResponseEntity.badRequest().body("E-mail já cadastrado.");
            }

            userProducer.sendCompanyCreation(data);
            return ResponseEntity.ok().body("Registro de empresa aceito.");
        }).onErrorResume(e -> {
            e.printStackTrace(); // log opcional
            return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar registro de empresa."));
        });
    }
}
