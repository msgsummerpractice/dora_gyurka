package com.example.spring_jpa.mapper;

import com.example.spring_jpa.dto.UpdateUserRequest;
import com.example.spring_jpa.dto.UserRequest;
import com.example.spring_jpa.dto.UserResponse;
import com.example.spring_jpa.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-28T08:36:42+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.46.100.v20260624-0231, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserRequest toRequest(User user) {
        if ( user == null ) {
            return null;
        }

        UserRequest userRequest = new UserRequest();

        userRequest.setFirstName( user.getFirstName() );
        userRequest.setLastName( user.getLastName() );
        userRequest.setUsername( user.getUsername() );
        userRequest.setEmail( user.getEmail() );
        userRequest.setPassword( user.getPassword() );

        return userRequest;
    }

    @Override
    public UserResponse toResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( user.getId() );
        userResponse.setUsername( user.getUsername() );
        userResponse.setFirstName( user.getFirstName() );
        userResponse.setLastName( user.getLastName() );
        userResponse.setEmail( user.getEmail() );

        return userResponse;
    }

    @Override
    public User toEntity(UserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( userRequest.getUsername() );
        user.setFirstName( userRequest.getFirstName() );
        user.setLastName( userRequest.getLastName() );
        user.setEmail( userRequest.getEmail() );

        return user;
    }

    @Override
    public User toEntity(UserResponse userResponse) {
        if ( userResponse == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( userResponse.getUsername() );
        user.setFirstName( userResponse.getFirstName() );
        user.setLastName( userResponse.getLastName() );
        user.setEmail( userResponse.getEmail() );

        return user;
    }

    @Override
    public User toEntity(UpdateUserRequest updateUserRequest) {
        if ( updateUserRequest == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( updateUserRequest.getUsername() );

        return user;
    }
}
