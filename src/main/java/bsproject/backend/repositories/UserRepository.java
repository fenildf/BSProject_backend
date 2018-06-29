package bsproject.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import bsproject.backend.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    User findByEmail(String email);
    boolean existsByName(String name);
    User findByName(String name);
}