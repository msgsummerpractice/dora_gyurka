package com.example.spring_jpa;

import org.junit.jupiter.api.Assertions;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;

import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import com.example.spring_jpa.exception.ResourceNotFoundException;
import com.example.spring_jpa.model.User;
import com.example.spring_jpa.repository.UserRepository;
import com.example.spring_jpa.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    
    @Mock
    private UserMapper userMapper;
    
    @Mock
    private UserRepository userRepository;

    private UserService userService;

    public User user;

    @BeforeEach
    public void setUp() {
        userService = new UserService(userRepository);

        user = new User(null,"JohnD","John", "Doe", "password", "john@email.com");
        userService.createUser(user);
    }

    @Test
    public void testCreateUser() {
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testGetUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Assertions.assertEquals(Optional.of(user), userService.getUserById(1L));
    }

    @Test
    public void testFindAllUsers() {
      when(userRepository.findAll()).thenReturn(List.of(new User(1L,"JohnD","John", "Doe", "password", "john@email.com")));
       //Pageable pageable = PageRequest.of(0, 2);
      List<User> users = userService.findAllUsers();
      Assertions.assertEquals(1, users.size());
    }

    @Test
    void testUpdateUser() {
        User user = new User(1L, "JaneD", "Jane", "Doe", "password", "jane@email.com", null,null);
 
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        Optional<User> result = userService.updateUser(user);

        assertEquals(Optional.of(user), result);

        verify(userRepository).findById(1L);
        verify(userRepository).save(user);
    }

    @Test
    public void testDeleteUser() {
        Long id = 16L;

        userService.deleteUser(id);

        verify(userRepository, times(1)).deleteById(id);
        assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(16L));
    }
}
