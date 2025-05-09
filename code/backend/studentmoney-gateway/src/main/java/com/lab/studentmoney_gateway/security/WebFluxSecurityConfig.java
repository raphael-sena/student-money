package com.lab.studentmoney_gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Configuration
@EnableReactiveMethodSecurity
public class WebFluxSecurityConfig {

    private final TokenService tokenService;

    public WebFluxSecurityConfig(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(csrf -> csrf.disable())
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .authenticationManager(authenticationManager())
                .securityContextRepository(securityContextRepository())
                .authorizeExchange(exchange -> exchange
                        .pathMatchers(HttpMethod.OPTIONS).permitAll()
                        .pathMatchers("/auth/**").permitAll()
                        .pathMatchers("/api/v1/users/**").permitAll()
                        .anyExchange().authenticated()
                )
                .build();
    }

    @Bean
    public ReactiveAuthenticationManager authenticationManager() {
        return authentication -> {
            String token = authentication.getCredentials().toString();
            String username = tokenService.validateToken(token);
            String role = tokenService.getClaim(token, "role");

            if (username == null || username.isBlank()) {
                return Mono.empty();
            }

            var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
            AbstractAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, token, authorities);
            return Mono.just(auth);
        };
    }

    @Bean
    public ServerSecurityContextRepository securityContextRepository() {
        return new BearerTokenSecurityContextRepository(tokenService);
    }
}