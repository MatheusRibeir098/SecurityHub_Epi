package org.example.backepi_projeto.controller;

import org.example.backepi_projeto.model.User;
import org.example.backepi_projeto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/cadastrar")
    public String showCadastroUsuarioForm(Model model) {
        model.addAttribute("user", new User());
        return "usuarios/cadastrar-usuario";
    }

    @PostMapping("/cadastrar")
    public String cadastrarUsuario(
            @RequestParam("nome") String nome,
            @RequestParam("email") String email,
            @RequestParam("senha") String senha,
            @RequestParam("tipo") String tipo,
            RedirectAttributes redirectAttributes) {

        User newUser = new User();
        newUser.setNome(nome);
        newUser.setUsername(email);
        newUser.setPassword(passwordEncoder.encode(senha));
        newUser.setRole(tipo.toUpperCase());

        userRepository.save(newUser);

        redirectAttributes.addFlashAttribute("mensagemSucesso", "Usuário cadastrado com sucesso!");
        return "redirect:/usuarios/listar";
    }

    @GetMapping("/listar")
    public String listarUsuarios(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "usuarios/lista-usuario";
    }

    @PostMapping("/excluir/{id}")
    public String excluirUsuario(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        // Lembre-se que se houver dependências de chave estrangeira (como a tabela 'emprestimo'),
        // você precisará lidar com elas antes de excluir o usuário.
        userRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Usuário excluído com sucesso!");
        return "redirect:/usuarios/listar";
    }

    @GetMapping("/atualizar/{id}")
    public String showAtualizarUsuarioForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            model.addAttribute("user", userOptional.get());
            return "usuarios/atualizar-usuario";
        } else {
            redirectAttributes.addFlashAttribute("mensagemErro", "Usuário não encontrado para atualização!");
            return "redirect:/usuarios/listar";
        }
    }

    @PostMapping("/atualizar")
    public String atualizarUsuario(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        Optional<User> existingUserOptional = userRepository.findById(user.getId());

        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setNome(user.getNome());
            existingUser.setUsername(user.getUsername());
            existingUser.setRole(user.getRole().toUpperCase());

            // Lógica para atualizar a senha se ela foi fornecida
            // Verifica se o campo 'password' do formulário não está vazio e não é nulo
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                // Criptografa a nova senha antes de salvar
                existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            // Se user.getPassword() for nulo ou vazio, a senha existente no existingUser será mantida.

            userRepository.save(existingUser);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Usuário atualizado com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao atualizar usuário: Usuário não encontrado!");
        }
        return "redirect:/usuarios/listar";
    }
}