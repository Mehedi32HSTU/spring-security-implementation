package com.javabeans.springsecurityimplementation;

import com.javabeans.springsecurityimplementation.request_response.MessageResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@SpringBootApplication
@RestController
public class SpringSecurityImplementationApplication {
	private static Date applicationStartDate;

	public static void main(String[] args) {
		applicationStartDate = new Date();
		SpringApplication.run(SpringSecurityImplementationApplication.class, args);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> getWelcomeMessage(){
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Welcome to SPRING-JWT-SECURITY-APPLICATION. " +
				"Application is running since : "+applicationStartDate));
	}

}
