package org.example.backepi_projeto.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/epis/**").authenticated() // Requer autenticação para caminhos que começam com /epis/
                        .anyRequest().permitAll() // Permite todas as outras requisições
                )
                .formLogin(form -> form // Configura o login de formulário (opcional, remova 'form -> form' se não for necessário)
                        .permitAll() // Permite que todos acessem a página de login
                )
                .logout(logout -> logout // Configura o logout
                        .permitAll() // Permite que todos acessem a página de logout
                );

        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("usuario")
                .password("123")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}