package com.example.controller;
import org.springframework.web.bind.annotation.RestController;
import com.example.config.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import com.example.model.User;
import com.example.service.UserService;

import jakarta.validation.Valid;
import lombok.Value;

import org.springframework.web.bind.annotation.PathVariable;

import org.slf4j.*;


import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/users")
public class UserController {

   @Autowired
   private UserService userService;

  

   private static final Logger logger = LoggerFactory.getLogger(UserController.class);

   @Autowired
   private ConfigProperties configProperties; 
   
    @GetMapping("/all")
    public List <User> getAllUsers() {
        logger.info("Fetching all users");
        return userService.getAllUsers();
    }

    @GetMapping("/email/{email}")
    public String getUserByEmail(@Valid @PathVariable("email") String email) {
        logger.info("Fetching user by email: {}", email);

        if(email.length() > 20) {
            logger.error("Email length exceeds maximum allowed length of 20 characters");
            return HttpStatus.BAD_REQUEST + " Email length exceeds maximum allowed length of 20 characters";
        }
        else {
            return userService.getUserByEmail(email);
        }
    }

    @GetMapping("/config")
    public String getConfigProperties() {
        logger.info("Fetching configuration properties");
        return "Server Port: " + configProperties.getPort();
    }
}
