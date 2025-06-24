package org.example.backepi_projeto.security;

import org.example.backepi_projeto.service.JpaUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JpaUserDetailsService jpaUserDetailsService;

    public SecurityConfig(JpaUserDetailsService jpaUserDetailsService) {
        this.jpaUserDetailsService = jpaUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Atenção: csrf().disable() não é recomendado para produção por questões de segurança
                .authorizeHttpRequests(authorize -> authorize
                        // Permissões públicas e recursos estáticos primeiro
                        .requestMatchers("/login", "/css/**", "/js/**", "/imagem/**", "/particles.js").permitAll()
                        .requestMatchers("/pagina-inicial1").permitAll()

                        // REGRAS ESPECÍFICAS DE AUTORIZAÇÃO (ANTES DO anyRequest())
                        // O endpoint de exclusão (POST) exige ROLE_ADMIN
                        .requestMatchers("/epis/excluir/**").hasRole("ADMIN")
                        // Se o cadastro também for só para ADMIN
                        .requestMatchers("/epis/cadastrar-epis").hasRole("ADMIN")
                        // Se listar for para qualquer usuário autenticado
                        .requestMatchers("/epis/listar").authenticated()

                        // QUALQUER OUTRA REQUISIÇÃO (após as específicas), exige autenticação
                        .anyRequest().authenticated()
                )
                .userDetailsService(jpaUserDetailsService) // Adicionei esta linha para garantir que seu UserDetailsService está sendo usado
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/pagina-inicial", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .rememberMe(rememberMe -> rememberMe.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://127.0.0.1:5500");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}