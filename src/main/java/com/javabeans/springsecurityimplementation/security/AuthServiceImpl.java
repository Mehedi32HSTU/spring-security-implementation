package com.javabeans.springsecurityimplementation.security;

import com.javabeans.springsecurityimplementation.request_response.AuthRequestDTO;
import com.javabeans.springsecurityimplementation.request_response.AuthResponseDTO;
import com.javabeans.springsecurityimplementation.request_response.MessageResponse;
import com.javabeans.springsecurityimplementation.security.jwt.JwtService;
import com.javabeans.springsecurityimplementation.users.UserInfo;
import com.javabeans.springsecurityimplementation.users.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class AuthServiceImpl implements AuthService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;
    @Override
    public ResponseEntity<?> authenticateAndGetToken(AuthRequestDTO authRequestDTO) {
        try {
            logger.info("<<<<<----------- authenticateUser is called ----------->>>>>");

            String username = authRequestDTO.getUsername();
            UserInfo userInfo = userInfoRepository.findByUsername(username);

            if(Objects.isNull(userInfo)) {
                logger.info("User : " + username + " Not Registered");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("User Not Registered"));
            }
            Authentication authentication = null;
            try {
                authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
            } catch(BadCredentialsException ex) {
                logger.info("Bad credentials exception occurred.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Bad credentials exception occurred."));
            }
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtToken = jwtService.generateToken(userInfo, authentication);

            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            UserInfo logedInUserInfo = userInfoRepository.findByUsername(username);
            List<String> roles = customUserDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new AuthResponseDTO(
                    logedInUserInfo.getId(),
                    logedInUserInfo.getFirstname(),
                    logedInUserInfo.getLastname(),
                    logedInUserInfo.getUsername(),
                    jwtToken,
                    roles));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("An exception is occurred, reason : "+e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Bad credentials exception occurred."));
        }
    }
}
