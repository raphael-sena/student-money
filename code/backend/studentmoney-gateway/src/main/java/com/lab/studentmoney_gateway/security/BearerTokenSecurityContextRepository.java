package com.lab.studentmoney_gateway.security;

import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;

public class BearerTokenSecurityContextRepository implements ServerSecurityContextRepository {

    private final TokenService tokenService;

    public BearerTokenSecurityContextRepository(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public Mono<Void> save(ServerWebExchange exchange, org.springframework.security.core.context.SecurityContext context) {
        return Mono.empty();
    }

    @Override
    public Mono<org.springframework.security.core.context.SecurityContext> load(ServerWebExchange exchange) {
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Mono.empty();
        }

        String token = authHeader.replace("Bearer ", "");
        String username = tokenService.validateToken(token);
        String role = tokenService.getClaim(token, "role");

        if (username == null || username.isBlank()) {
            return Mono.empty();
        }

        var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
        var auth = new UsernamePasswordAuthenticationToken(username, token, authorities);
        return Mono.just(new SecurityContextImpl(auth));
    }
}

