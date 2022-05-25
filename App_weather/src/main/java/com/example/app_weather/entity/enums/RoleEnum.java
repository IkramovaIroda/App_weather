package com.example.app_weather.entity.enums;

public enum RoleEnum {

    ROLE_USER("user"),
    ROLE_ADMIN("admin");
    private String description;

    RoleEnum(String description) {
        this.description = description;
    }
}
