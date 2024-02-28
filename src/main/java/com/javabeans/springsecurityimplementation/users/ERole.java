package com.javabeans.springsecurityimplementation.users;

public enum ERole {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private final String role;

    private ERole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
