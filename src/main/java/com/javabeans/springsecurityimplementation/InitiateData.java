package com.javabeans.springsecurityimplementation;

import com.javabeans.springsecurityimplementation.constants_and_enums.MenuIdContainer;
import com.javabeans.springsecurityimplementation.menu_permissions.MenuPermissionUtil;
import com.javabeans.springsecurityimplementation.menu_permissions.MenuPermissionRepository;
import com.javabeans.springsecurityimplementation.users.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;

@Component
public class InitiateData implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${com.javabeans.username.admin}")
    private String ADMIN_USERNAME;
    @Value("${com.javabeans.password.admin}")
    private String ADMIN_PASSWORD;
    @Value("${com.javabeans.firstname.admin}")
    private String ADMIN_FIRSTNAME;
    @Value("${com.javabeans.lastname.admin}")
    private String ADMIN_LASTNAME;
    @Value("${com.javabeans.email.admin}")
    private String ADMIN_EMAIL;

    @Value("${com.javabeans.username.user}")
    private String USER_USERNAME;
    @Value("${com.javabeans.password.user}")
    private String USER_PASSWORD;
    @Value("${com.javabeans.firstname.user}")
    private String USER_FIRSTNAME;
    @Value("${com.javabeans.lastname.user}")
    private String USER_LASTNAME;
    @Value("${com.javabeans.email.user}")
    private String USER_EMAIL;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MenuPermissionRepository menuPermissionRepository;

    @Override
    public void run(String... args) throws Exception {
        try {
            MenuPermissionUtil menuPermissionUtil = new MenuPermissionUtil(menuPermissionRepository);
            if(userInfoRepository.findAll().isEmpty()) {
                logger.info("InitiateData run is called.");
                UserRole adminRole = new UserRole();
                adminRole.setName(ERole.ROLE_ADMIN.getRole());
                UserRole userRole = new UserRole();
                userRole.setName(ERole.ROLE_USER.getRole());
                adminRole = userRoleRepository.save(adminRole);
                userRole = userRoleRepository.save(userRole);

                UserInfo adminUser = new UserInfo();
                adminUser.setFirstname(ADMIN_FIRSTNAME);
                adminUser.setLastname(ADMIN_LASTNAME);
                adminUser.setEmail(ADMIN_EMAIL);
                adminUser.setUsername(ADMIN_USERNAME);
                adminUser.setPassword(passwordEncoder.encode(ADMIN_PASSWORD));
                adminUser.setRoles(Collections.singletonList(adminRole));

                UserInfo userInfo = new UserInfo();
                userInfo.setFirstname(USER_FIRSTNAME);
                userInfo.setLastname(USER_LASTNAME);
                userInfo.setEmail(USER_EMAIL);
                userInfo.setUsername(USER_USERNAME);
                userInfo.setPassword(passwordEncoder.encode(USER_PASSWORD));
                userInfo.setRoles(Collections.singletonList(userRole));

                userInfoRepository.save(adminUser);
                userInfoRepository.save(userInfo);

                // TODO: Insert menu permission to admin and user (DONE)
                menuPermissionUtil.insertMenuPermission(adminUser.getId(), MenuIdContainer.getAllMenuIds());

                menuPermissionUtil.insertMenuPermission(userInfo.getId(),
                        Arrays.asList(MenuIdContainer.GET_USER_INFO, MenuIdContainer.GET_MENU_PERMISSION));
            }
            // TODO: load all Menu Permissions (DONE)
            menuPermissionUtil.loadMenuPermission();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("An exception is occurred, reason : "+e.getMessage());
        }
    }
}
