package dev.dmohindru.springsecurityjpa.repository;

import dev.dmohindru.springsecurityjpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName);

}
