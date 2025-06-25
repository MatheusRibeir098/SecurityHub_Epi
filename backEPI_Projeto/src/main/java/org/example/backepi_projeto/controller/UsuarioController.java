package org.example.backepi_projeto.controller;

import org.example.backepi_projeto.model.User;
import org.example.backepi_projeto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/usuarios") // Define o prefixo para todas as URLs neste controlador
public class UsuarioController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Usado para criptografar a senha

    // Exibe o formulário de cadastro de usuário
    @GetMapping("/cadastrar")
    public String showCadastroUsuarioForm(Model model) {
        model.addAttribute("user", new User()); // Adiciona um novo objeto User para o formulário
        return "usuarios/cadastrar-usuario"; // Retorna o nome do template HTML
    }

    // Processa o envio do formulário de cadastro de usuário
    @PostMapping("/cadastrar")
    public String cadastrarUsuario(
            @RequestParam("nome") String nome,
            @RequestParam("email") String email,
            @RequestParam("senha") String senha,
            @RequestParam("tipo") String tipo,
            RedirectAttributes redirectAttributes) {

        // Cria um novo objeto User
        User newUser = new User();
        newUser.setNome(nome);
        newUser.setUsername(email); // O email é o username para o Spring Security
        newUser.setPassword(passwordEncoder.encode(senha)); // Criptografa a senha antes de salvar
        newUser.setRole(tipo.toUpperCase()); // Garante que a role seja em maiúsculas (ex: "ADMIN", "USUARIO")

        userRepository.save(newUser); // Salva o novo usuário no banco de dados

        redirectAttributes.addFlashAttribute("mensagemSucesso", "Usuário cadastrado com sucesso!");

        // Redireciona para a página de sucesso ou para a lista de usuários
        return "redirect:/usuarios/listar"; // Você pode mudar para uma página de sucesso, se tiver
    }
}