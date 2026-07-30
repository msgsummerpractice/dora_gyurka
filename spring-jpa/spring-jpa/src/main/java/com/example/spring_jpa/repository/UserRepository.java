package com.example.spring_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.spring_jpa.model.User;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   User findByEmail(String email);

   @Query("SELECT u FROM User u ORDER BY LOWER(u.username) ASC LIMIT 10")
    List<User> findTop10UsersCaseInsensitive();

    @Query("SELECT COUNT(u.id) FROM User u")
    Long countUsers();
}
