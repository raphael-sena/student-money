package com.lab.studentmoney_gateway.producers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.studentmoney_gateway.config.RabbitMQConfig;
import com.lab.studentmoney_gateway.dtos.users.company.CompanyCreateRequestDTO;
import com.lab.studentmoney_gateway.dtos.users.person.student.StudentCreateRequestDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserProducer {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public UserProducer(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendStudentCreation(StudentCreateRequestDTO dto) {
        try {
            String json = objectMapper.writeValueAsString(dto);
            rabbitTemplate.convertAndSend(RabbitMQConfig.STUDENT_QUEUE, json);
        } catch (Exception e) {
            System.err.println("❌ Erro ao serializar student: " + e.getMessage());
        }
    }

    public void sendCompanyCreation(CompanyCreateRequestDTO dto) {
        try {
            String json = objectMapper.writeValueAsString(dto);
            rabbitTemplate.convertAndSend(RabbitMQConfig.COMPANY_QUEUE, json);
        } catch (Exception e) {
            System.err.println("❌ Erro ao serializar student: " + e.getMessage());
        }
    }
}
