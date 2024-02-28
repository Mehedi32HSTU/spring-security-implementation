package com.javabeans.springsecurityimplementation.security;

import com.javabeans.springsecurityimplementation.users.UserInfo;
import com.javabeans.springsecurityimplementation.users.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SecurityService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserInfoRepository userInfoRepository;

    public UserInfo getAuthenticatedUser() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            return userInfoRepository.findByUsername(userDetails.getUsername());
        } catch (Exception e) {
            logger.error("Exception {} has occurred in getAuthorizedUser method.", e.getMessage());
            return null;
        }
    }
    public boolean isAllowedToRequest() {
        try {
            UserInfo authenticatedUserInfo = getAuthenticatedUser();

            return Objects.nonNull(authenticatedUserInfo);
        } catch (Exception e) {
            logger.error("Exception {} has occurred in isUserApprovedActive method.", e.getMessage());
            return false;
        }
    }
}
