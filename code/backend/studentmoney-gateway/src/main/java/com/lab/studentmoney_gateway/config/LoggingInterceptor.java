package com.lab.studentmoney_gateway.config;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.springframework.http.HttpRequest;

public class LoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        org.springframework.http.client.ClientHttpRequestExecution execution)
            throws IOException {

        System.out.println("➡️ Enviando request:");
        System.out.println("URI: " + request.getURI());
        System.out.println("Método: " + request.getMethod());
        System.out.println("Headers: " + request.getHeaders());
        System.out.println("Body: " + new String(body, StandardCharsets.UTF_8));

        ClientHttpResponse response = execution.execute(request, body);

        System.out.println("⬅️ Resposta:");
        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Headers: " + response.getHeaders());

        String bodyString = new BufferedReader(
                new InputStreamReader(response.getBody(), StandardCharsets.UTF_8))
                .lines().collect(Collectors.joining("\n"));
        System.out.println("Body: " + bodyString);

        return response;
    }
}
