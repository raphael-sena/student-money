package com.lab.control_service.clients;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class UserClient {

    private final WebClient webClient;

    public UserClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public boolean companyExists(Long companyId) {
        try {
            webClient.get()
                    .uri("/api/v1/users/company/{id}", companyId)
                    .retrieve()
                    .toBodilessEntity()
                    .block();
            return true;
        } catch (WebClientResponseException.NotFound e) {
            return false;
        }
    }

    public boolean userExists(String email) {
        return Boolean.TRUE.equals(webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/users/exists")
                        .queryParam("email", email)
                        .build())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block());
    }


}