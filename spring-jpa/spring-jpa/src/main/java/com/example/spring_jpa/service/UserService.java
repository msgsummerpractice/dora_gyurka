package com.example.spring_jpa.service;

import com.example.spring_jpa.repository.UserRepository;
import com.example.spring_jpa.model.User;
import com.example.spring_jpa.exception.ResourceNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).getContent();
    }


    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        try{
             return userRepository.findById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
    }

    public void deleteUser(Long id) {
        try{
            if (!userRepository.existsById(id)) {
                throw new ResourceNotFoundException("User not found with id: " + id);
            } else {
                userRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
        return userRepository.findByEmail(email);
    }

    public Optional<User> updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        if (existingUser != null) {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setEmail(user.getEmail());
            return Optional.of(userRepository.save(existingUser));
        }
        return Optional.empty();
    }

    public List<User> findTop10Users() {
         return userRepository.findFirst10ByOrderByUsernameIgnoreCaseAsc();
    }

    public Long countUsers() {
        return userRepository.countUsers();
    }

}
