package com.lab.user_service.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.user_service.configs.RabbitMQConfig;
import com.lab.user_service.entities.dtos.users.company.CompanyCreateRequestDTO;
import com.lab.user_service.entities.dtos.users.person.student.StudentCreateRequestDTO;
import com.lab.user_service.services.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserConsumer {

    private final UserService userService;
    private final ObjectMapper objectMapper;

    public UserConsumer(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = RabbitMQConfig.STUDENT_QUEUE)
    public void handleStudentCreation(String json) {
        try {
            StudentCreateRequestDTO dto = objectMapper.readValue(json, StudentCreateRequestDTO.class);
            userService.createStudent(dto);
            System.out.println("✅ Student created: " + dto.person().user().email());
        } catch (Exception e) {
            System.err.println("❌ Erro ao processar mensagem de student: " + e.getMessage());
        }
    }

    @RabbitListener(queues = RabbitMQConfig.COMPANY_QUEUE)
    public void handleCompanyCreation(String json) {
        try {
            CompanyCreateRequestDTO dto = objectMapper.readValue(json, CompanyCreateRequestDTO.class);
            userService.createCompany(dto);
            System.out.println("✅ Company created: " + dto.user().email());
        } catch (Exception e) {
            System.err.println("❌ Erro ao processar mensagem de company: " + e.getMessage());
        }
    }
}
