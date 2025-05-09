package com.lab.studentmoney_gateway.clients;

import com.lab.studentmoney_gateway.dtos.users.AuthenticationDTO;
import com.lab.studentmoney_gateway.dtos.users.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserClient {

    private final RestTemplate restTemplate;
    private final String userServiceUrl;

    public UserClient(RestTemplate restTemplate, @Value("${user.service.url}") String userServiceUrl) {
        this.restTemplate = restTemplate;
        this.userServiceUrl = userServiceUrl;
    }

    public boolean userExists(String email) {
        Boolean exists = restTemplate.getForObject(userServiceUrl + "/api/v1/users/exists?email=" + email, Boolean.class);
        return exists != null && exists;
    }

    public UserDTO validateCredentials(AuthenticationDTO dto) {
        return restTemplate.postForObject("http://user-service/auth/validate", dto, UserDTO.class);
    }
}
