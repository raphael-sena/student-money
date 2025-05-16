package com.lab.user_service.repositories;

import com.lab.user_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByUsername(String login);

    boolean existsByUsernameOrEmail(String username, String email);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
