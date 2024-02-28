package com.javabeans.springsecurityimplementation.controllers;

import com.javabeans.springsecurityimplementation.users.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/request")
public class RequestController {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/testAdmin", method = RequestMethod.GET)
    public ResponseEntity<?> testAdmin() {
        return ResponseEntity.ok().body(userInfoRepository.findAll());
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/testUser", method = RequestMethod.GET)
    public ResponseEntity<?> testUser() {
        return ResponseEntity.ok().body(userInfoRepository.findAll());
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<?> test() {
        return ResponseEntity.ok().body(userInfoRepository.findAll());
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or @securityService.isAllowedToRequest()")
    @RequestMapping(value = "/test-all-access", method = RequestMethod.GET)
    public ResponseEntity<?> testAllAccess() {
        return ResponseEntity.ok().body(userInfoRepository.findAll());
    }

}
