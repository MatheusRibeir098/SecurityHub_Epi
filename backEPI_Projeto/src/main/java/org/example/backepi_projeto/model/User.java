package org.example.backepi_projeto.model;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "usuario")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "nome", nullable = false) // NOVO CAMPO: Mapeia para a coluna 'nome'
    private String nome; // Adicione esta linha

    @Column(name = "email", unique = true, nullable = false)
    private String username;

    @Column(name = "senha", nullable = false)
    private String password;

    @Column(name = "perfil", nullable = false)
    private String role;

    // Construtores
    public User() {
    }

    public User(String nome, String username, String password, String role) { // ATUALIZE ESTE CONSTRUTOR
        this.nome = nome; // Adicione esta linha
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; } // NOVO GETTER
    public void setNome(String nome) { this.nome = nome; } // NOVO SETTER

    @Override
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    @Override
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}