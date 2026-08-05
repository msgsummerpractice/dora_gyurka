package com.example.spring_jpa.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.spring_jpa.model.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findTopByUsernameAndUsedFalseOrderByIdDesc(String username);

}
