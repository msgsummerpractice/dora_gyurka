package com.example.repository;
import java.util.concurrent.ConcurrentHashMap;
import com.example.model.User;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class UserRepository {
    
    private ConcurrentHashMap<String, User> userMap = new ConcurrentHashMap<>();

    public UserRepository() {
         userMap.put("john@email.com", new User("John Doe", "johnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn@email.com"));
         userMap.put("jane@email.com", new User("Jane Doe", "jane@email.com"));
    }

    public List<User> getAllUsers() {
       // log.info("hello from repo");
        return userMap.values().stream().toList();
    }

    public String getUserByEmail(String email) {
        log.info("Fetching user by email: {}", email);
        return userMap.get(email) != null ? userMap.get(email).getName() : "User not found";
    }

    public void updateUser(String email, String name) {
        User user = userMap.get(email);
        if (user != null) {
            user.setName(name);
        }
    }

    public void deleteUser(String email) {
       if(userMap.containsKey(email)) {
            userMap.remove(email);
        }
    }
}
