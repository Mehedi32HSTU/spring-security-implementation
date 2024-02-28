package com.javabeans.springsecurityimplementation.security;

import com.javabeans.springsecurityimplementation.request_response.AuthRequestDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    public ResponseEntity<?> authenticateAndGetToken(AuthRequestDTO authRequestDTO);

}
