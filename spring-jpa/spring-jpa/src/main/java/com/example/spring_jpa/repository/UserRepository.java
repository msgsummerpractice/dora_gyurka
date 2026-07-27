package com.example.spring_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.spring_jpa.model.User;
import java.util.List;
public interface UserRepository extends JpaRepository<User, Long> {
    
    public User getUserByEmail(String email);

    public List<User> findFirst10ByOrderByUsernameAsc();


    @Query("SELECT COUNT(u.id) FROM User u")
    public int countUsers();
}
