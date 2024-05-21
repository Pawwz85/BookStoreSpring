package org.example.JPARepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String userName);
    User save(User user);
}
