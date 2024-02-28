package com.javabeans.springsecurityimplementation.controllers;

import com.javabeans.springsecurityimplementation.users.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('ROLE_USER')")
public class UserAccessController {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @RequestMapping(value = "/testUser", method = RequestMethod.GET)
    public ResponseEntity<?> testUser() {
        return ResponseEntity.ok().body(userInfoRepository.findAll());
    }

}
