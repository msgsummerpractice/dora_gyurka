package com.example.spring_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.spring_jpa.model.User;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   User findByEmail(String email);
   
   Optional<User> findByUsername(String username);


    @Query("SELECT u FROM User u ORDER BY LOWER(u.username) ASC")
    List<User> findFirst10ByOrderByUsernameIgnoreCaseAsc();


    @Query("SELECT COUNT(u.id) FROM User u")
    Long countUsers();
}