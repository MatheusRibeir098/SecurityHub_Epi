package org.example.backepi_projeto.controller;

import org.example.backepi_projeto.model.EPI;
import org.example.backepi_projeto.repository.EpiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private EpiRepository epiRepository;

    // Serve a página inicial. O retorno é APENAS "pagina-inicial"
    @GetMapping("/pagina-inicial")
    public String homePage() {
        return "pagina-inicial"; // AGORA CORRETO
    }

    @GetMapping("/pagina-inicial1")
    public String homePage1() {
        return "pagina-inicial1"; // AGO    RA CORRETO
    }

    @GetMapping("/epi/listar")
    public String listarEpis(Model model) {
        List<EPI> epis = epiRepository.findAll(); // Busca todos os EPIs do banco de dados
        model.addAttribute("epis", epis); // Adiciona a lista de EPIs ao modelo com o nome "epis"
        return "epis/lista-epis"; // Retorna o nome do template Thymeleaf
    }

    // Serve a página de login
    @GetMapping("/login")
    public String login() {
        return "login"; // JÁ ESTAVA CORRETO
    }

    // Serve a página de cadastro de EPIs.
    // Se cadastrar-epis.html estiver em templates/epis/cadastrar-epis.html
    @GetMapping("/epis/cadastrar-epis")
    public String cadastrarEpisPage() {
        return "epis/cadastrar-epis"; // AGORA CORRETO
    }

    // Continue ajustando os outros Controllers que você possa ter:
    // Por exemplo, se tiver 'templates/usuarios/lista-usuarios.html'
    @GetMapping("/usuarios/lista-usuarios")
    public String listarUsuarios() {
        return "usuarios/lista-usuarios";
    }

    // Ajuste para outras pastas conforme sua estrutura (devolucao, emprestimo)
    // Ex: templates/devolucao/lista-devolucao.html
    @GetMapping("/devolucao/lista-devolucao")
    public String listarDevolucao() {
        return "devolucao/lista-devolucao";
    }

    // Ex: templates/emprestimo/lista-emprestimo.html
    @GetMapping("/emprestimo/lista-emprestimo")
    public String listarEmprestimo() {
        return "emprestimo/lista-emprestimo";
    }
}