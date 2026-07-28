package com.example.spring_jpa.controller;

import org.springframework.http.MediaType;
import com.example.spring_jpa.dto.UpdateUserRequest;
import com.example.spring_jpa.service.UserService;
import com.example.spring_jpa.model.User;
import com.example.spring_jpa.dto.UserRequest;
import com.example.spring_jpa.dto.UserResponse;
import com.example.spring_jpa.exception.ResourceNotFoundException;
import com.example.spring_jpa.mapper.UserMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/users")
@Validated
public class UserController {


    private final UserService userService;

    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();

        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest ) {
        UserResponse user1 = userMapper.toResponse(userService.createUser(userMapper.toEntity(userRequest)));
        if (user1 != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(user1);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id).orElse(null);
        if (user == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            UserResponse response = userMapper.toResponse(user);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

    }

    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest user) {
        User userEntity = userMapper.toEntity(user);
        userEntity.setId(id);
        Optional<User> updatedUser = userService.updateUser(userEntity);

        if (!updatedUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(userMapper.toResponse(updatedUser.get()));
        }
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PatchMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponse> partialUpdateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest user) {
        Optional<UserResponse> user1 = Optional.ofNullable(userMapper.toResponse(userService.getUserById(id).orElse(null)));

        if (!user1.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            if(user.getUsername() != null){
                user1.get().setUsername(user.getUsername());
            }
            return ResponseEntity.status(HttpStatus.OK).body(user1.get());
        }
       

    }
}