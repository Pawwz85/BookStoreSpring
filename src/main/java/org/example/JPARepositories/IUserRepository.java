package org.example.JPARepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String userName);
    boolean existsByUsername(String username);
    User save(User user);
}
