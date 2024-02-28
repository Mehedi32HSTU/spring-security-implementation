package com.javabeans.springsecurityimplementation.controllers;

import com.javabeans.springsecurityimplementation.users.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminAccessController {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @RequestMapping(value = "/testAdmin", method = RequestMethod.GET)
    public ResponseEntity<?> testAdmin() {
        return ResponseEntity.ok().body(userInfoRepository.findAll());
    }

}
