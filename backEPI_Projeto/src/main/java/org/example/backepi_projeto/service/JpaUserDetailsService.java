package org.example.backepi_projeto.service;

import org.example.backepi_projeto.model.User;
import org.example.backepi_projeto.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // Marca esta classe como um componente de serviço gerenciado pelo Spring
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // Injeção de dependência do UserRepository
    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca o usuário pelo username (que mapeia para o 'email' no seu banco)
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
        // Como sua entidade User já implementa UserDetails, você pode retorná-la diretamente
        return user;
    }
}