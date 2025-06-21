package org.example.backepi_projeto.repository;

import org.example.backepi_projeto.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Spring Data JPA criará automaticamente a query para encontrar um usuário pelo username (email)
    Optional<User> findByUsername(String username);
}