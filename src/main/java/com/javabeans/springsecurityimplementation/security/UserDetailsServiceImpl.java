package com.javabeans.springsecurityimplementation.security;

import com.javabeans.springsecurityimplementation.users.UserInfo;
import com.javabeans.springsecurityimplementation.users.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoRepository.findByUsername(username);
        if(userInfo == null){
            logger.error("Username not found: " + username);
            throw new UsernameNotFoundException("could not found user..!!");
        }
        return new CustomUserDetails(userInfo);
    }
}
