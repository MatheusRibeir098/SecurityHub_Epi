package org.example.backepi_projeto.controller;

import org.example.backepi_projeto.model.EPI;
import org.example.backepi_projeto.repository.EpiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
@Controller
public class HomeController {

    @Autowired
    private EpiRepository epiRepository;

//----------------- PAGINA INICIAL ----------
    @GetMapping("/pagina-inicial")
    public String homePage() {
        return "pagina-inicial";
    }

    @GetMapping("/pagina-inicial1")
    public String homePage1() {
        return "pagina-inicial1"; // AGO    RA CORRETO
    }
// -----------------------------------------

// ------------- PAGINAS LOGIN ----------------
    @GetMapping("/login")
    public String login() {
        return "login"; // JÁ ESTAVA CORRETO
    }
// ----------------------------------------

// -------- PAGINAS DE EPI (CRUD) -------------------
    @GetMapping("/epi/listar")
    public String listarEpis(Model model) {
        List<EPI> epis = epiRepository.findAll();
        model.addAttribute("epis", epis);
        return "epis/lista-epis";
    }

    @GetMapping("/epis/cadastrar-epis")
    public String cadastrarEpisPage() {
        return "epis/cadastrar-epis";
    }

    @PostMapping("/excluir/{id}")
    public String excluirEpi(@PathVariable Long id) {
        Optional<EPI> epiOptional = epiRepository.findById(id);
        if (epiOptional.isPresent()) {
            epiRepository.delete(epiOptional.get());
        }

        return "redirect:/epis/listar";
    }

// -----------------------------------

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