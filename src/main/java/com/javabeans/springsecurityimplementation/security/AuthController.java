package com.javabeans.springsecurityimplementation.security;

import com.javabeans.springsecurityimplementation.request_response.AuthRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){

        return authService.authenticateAndGetToken(authRequestDTO);
    }
}
