package com.javabeans.springsecurityimplementation.request_response;

import java.util.ArrayList;
import java.util.List;

public class AuthResponseDTO {
    private Long userId;
    private String firstname;
    private String lastname;
    private String username;
    private String token;
    private List<String> roles = new ArrayList<>();

    public AuthResponseDTO() {

    }
    public AuthResponseDTO(Long userId, String firstname, String lastname, String username, String token, List<String> roles) {
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.token = token;
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }


    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
