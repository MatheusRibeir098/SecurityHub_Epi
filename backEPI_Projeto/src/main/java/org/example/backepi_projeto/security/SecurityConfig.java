package org.example.backepi_projeto.security;

import org.example.backepi_projeto.service.JpaUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
// Remover importações não utilizadas, como:
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.web.authentication.AuthenticationSuccessHandler; // Se não for diretamente utilizada como um tipo aqui, mas sim o bean injetado

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JpaUserDetailsService jpaUserDetailsService; // MUDANÇA: Final
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler; // MUDANÇA: Final

    // MUDANÇA: Injeção por construtor
    public SecurityConfig(JpaUserDetailsService jpaUserDetailsService, CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
        this.jpaUserDetailsService = jpaUserDetailsService;
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**").disable()) // Adicionado .disable() se não for configurado o CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/css/**", "/js/**", "/imagem/**", "/particles.js").permitAll() // Usando '/imagem' para coincidir com as pastas
                        .requestMatchers("/login", "/cadastro", "/").permitAll()
                        .requestMatchers("/usuarios/cadastrar").permitAll() // Caminho correto para cadastro de usuário
                        .requestMatchers("/gerenciadores").hasRole("ADMIN")
                        .requestMatchers("/epis/**").hasRole("ADMIN")
                        .requestMatchers("/usuarios/**").hasRole("ADMIN") // Geral para gerenciar usuários, pode precisar de mais detalhes.
                        .requestMatchers("/gerenciadores-usuario/**").hasRole("USUARIO")
                        .anyRequest().authenticated()
                )
                .userDetailsService(jpaUserDetailsService) // Adicione esta linha para garantir que seu UserDetailsService está sendo usado
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(customAuthenticationSuccessHandler)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .rememberMe(rememberMe -> rememberMe.disable()) // Preferível desabilitar explicitamente se não for usar
                // MUDANÇA: Correção para FrameOptions depreciado
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
                .cors(withDefaults()) // Usar withDefaults() se você já tem um CorsConfigurationSource bean
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // REMOVIDO: Este método não é mais necessário com a injeção do JpaUserDetailsService diretamente no SecurityFilterChain
    // @Autowired
    // public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    //    auth.userDetailsService(jpaUserDetailsService).passwordEncoder(passwordEncoder());
    // }
}