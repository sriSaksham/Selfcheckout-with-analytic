package com.reader.scanner.repository;

import com.reader.scanner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    //User findByEmail(String email);
    Optional<User> findByEmail(String email);
}

