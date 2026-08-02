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

   List<User> findTop10ByUsernameContainingIgnoreCaseOrderByUsernameAsc(String username);

    @Query("SELECT COUNT(u.id) FROM User u")
    Long countUsers();
}