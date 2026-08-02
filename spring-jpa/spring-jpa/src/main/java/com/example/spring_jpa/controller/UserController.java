package com.example.spring_jpa.controller;

import org.springframework.http.MediaType;
import com.example.spring_jpa.dto.UpdateUserRequest;
import com.example.spring_jpa.service.UserService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;

import com.example.spring_jpa.model.User;
import com.example.spring_jpa.dto.UserRequest;
import com.example.spring_jpa.dto.UserResponse;
import com.example.spring_jpa.exception.ResourceNotFoundException;
import com.example.spring_jpa.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/users")
@Validated
@OpenAPIDefinition
public class UserController {

    private final UserService userService;

    @Autowired
    private UserMapper userMapper;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get all users", description = "Retrieve a list of all users")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list of users"),
        @ApiResponse(responseCode = "204", description = "No users found")
    })
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsers() {
        Pageable pageable = PageRequest.of(0, 2);
        List<User> users = userService.findAllUsers(pageable);

        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @Operation(summary = "Create user", description = "Create a new user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successfully created user"),
        @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest ) {
        UserResponse user1 = userMapper.toResponse(userService.createUser(userMapper.toEntity(userRequest)));
        if (user1 != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(user1);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @Operation(summary = "Get a user", description = "Retrieve a user by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved user"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id).orElse(null);
        if (user == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            UserResponse response = userMapper.toResponse(user);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

    }

    @Operation(summary = "Update user", description = "Update an existing user by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated user"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @Operation(summary = "Delete user", description = "Delete an existing user by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successfully deleted user"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @Operation(summary = "Partially update user", description = "Partially update an existing user by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated user"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> partialUpdateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest user) {
        Optional<UserResponse> user1 = Optional.ofNullable(userMapper.toResponse(userService.getUserById(id).orElse(null)));

        if (!user1.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            if(user.getUsername() != null){
                user1.get().setUsername(user.getUsername());
                userService.updateUser(userMapper.toEntity(user1.get()));
            }
            return ResponseEntity.status(HttpStatus.OK).body(user1.get());
        }

    }

}