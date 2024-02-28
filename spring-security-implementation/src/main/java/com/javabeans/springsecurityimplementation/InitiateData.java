package com.javabeans.springsecurityimplementation;

import com.javabeans.springsecurityimplementation.users.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class InitiateData implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        try {
            if(userInfoRepository.findAll().isEmpty()) {
                logger.info("InitiateData run is called.");
                UserRole adminRole = new UserRole();
                adminRole.setName(ERole.ROLE_ADMIN.getRole());
                UserRole userRole = new UserRole();
                userRole.setName(ERole.ROLE_USER.getRole());
                adminRole = userRoleRepository.save(adminRole);
                userRole = userRoleRepository.save(userRole);

                UserInfo adminUser = new UserInfo();
                adminUser.setFirstname("Admin");
                adminUser.setLastname("Admin");
                adminUser.setEmail("admin@admin.com");
                adminUser.setUsername("default_admin");
                adminUser.setPassword(passwordEncoder.encode("admin_1234"));
                adminUser.setRoles(Collections.singletonList(adminRole));

                UserInfo userInfo = new UserInfo();
                userInfo.setFirstname("User");
                userInfo.setLastname("User");
                userInfo.setEmail("user@user.com");
                userInfo.setUsername("default_user");
                userInfo.setPassword(passwordEncoder.encode("user_1234"));
                userInfo.setRoles(Collections.singletonList(userRole));

                userInfoRepository.save(adminUser);
                userInfoRepository.save(userInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("An exception is occurred, reason : "+e.getMessage());
        }
    }
}
