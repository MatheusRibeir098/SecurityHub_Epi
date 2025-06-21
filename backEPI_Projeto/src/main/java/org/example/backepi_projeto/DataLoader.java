package org.example.backepi_projeto.config;

import org.example.backepi_projeto.model.User;
import org.example.backepi_projeto.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Verificar se o usuário "matheus@" já existe para evitar duplicatas
            if (userRepository.findByUsername("matheus3@").isEmpty()) {
                User adminUser = new User();
                adminUser.setNome("Matheus Admin"); // Adicione esta linha para o campo 'nome'
                adminUser.setUsername("matheus3@");
                adminUser.setPassword(passwordEncoder.encode("123"));
                adminUser.setRole("ADMIN");
                userRepository.save(adminUser);
                System.out.println("Usuário ADMIN 'matheus3@' criado com sucesso!");
            } else {
                System.out.println("Usuário 'matheus@' já existe. Pulando criação.");
            }

            // Exemplo de outro usuário
            if (userRepository.findByUsername("matheus2@").isEmpty()) {
                User normalUser = new User();
                normalUser.setNome("Matheus Usuario"); // Adicione esta linha para o campo 'nome'
                normalUser.setUsername("matheus2@");
                normalUser.setPassword(passwordEncoder.encode("123"));
                normalUser.setRole("USUARIO");
                userRepository.save(normalUser);
                System.out.println("Usuário USUARIO 'matheus2@' criado com sucesso!");
            } else {
                System.out.println("Usuário 'matheus2@' já existe. Pulando criação.");
            }
        };
    }
}