package org.example.backepi_projeto.repository;

import org.example.backepi_projeto.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // ALTERADO: De findByEmail para findByUsername para corresponder ao campo 'username' no modelo User
    Optional<User> findByUsername(String username);
}