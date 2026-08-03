package com.example.spring_jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.spring_jpa.dto.UpdateUserRequest;
import com.example.spring_jpa.dto.UserRequest;
import com.example.spring_jpa.model.User;
import com.example.spring_jpa.dto.UserResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    
    @Mapping(target = "password", ignore = true)
    UserRequest toRequest(User user);
    
    UserResponse toResponse(User user);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "name", ignore = true)
    User toEntity(UserRequest userRequest);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "name", ignore = true)
    User toEntity(UserResponse userResponse);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "firstName", ignore = true)
    @Mapping(target = "lastName", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "name", ignore = true)
    User toEntity(UpdateUserRequest updateUserRequest);
}
