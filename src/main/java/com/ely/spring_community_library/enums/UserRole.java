package com.ely.spring_community_library.enums;

public enum UserRole {
    MANAGER("manager"),
    USER("user");

    private final String role;

    UserRole(String role) {this.role = role;}

    public String getRole() {
        return role;
    }
}
