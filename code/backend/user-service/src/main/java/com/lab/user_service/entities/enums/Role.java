package com.lab.user_service.entities.enums;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("admin"),
    STUDENT("student"),
    PROFESSOR("professor"),
    COMPANY("company");

    private final String role;

    Role(String role){
        this.role = role;
    }

}