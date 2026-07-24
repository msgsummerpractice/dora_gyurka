package com.example.repository;
import java.util.concurrent.ConcurrentHashMap;
import com.example.model.User;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    
    private ConcurrentHashMap<String, User> userMap = new ConcurrentHashMap<>();

    public void createUser(String name, String email) {
       userMap.put(email, new User(name, email));
    }

    public List<User> getAllUsers() {
        return userMap.values().stream().toList();
    }

    public User getUserByEmail(String email) {
        return userMap.get(email);
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
