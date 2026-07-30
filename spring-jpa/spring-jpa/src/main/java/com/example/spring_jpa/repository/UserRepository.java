package com.example.spring_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.spring_jpa.model.User;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   User findByEmail(String email);

    List<User> findTop10ByUsernameContainingIgnoreCaseOrderByUsernameAsc(String username);

    @Query("SELECT COUNT(u.id) FROM User u")
    Long countUsers();
}
