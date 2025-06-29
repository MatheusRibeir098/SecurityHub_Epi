package org.example.backepi_projeto.controller;

import org.example.backepi_projeto.model.EPI; // Pode remover esta importação se não for mais usada
import org.example.backepi_projeto.repository.EpiRepository; // Pode remover esta importação se não for mais usada
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private EpiRepository epiRepository;

    //----------------- PAGINA INICIAL ----------
    @GetMapping("/pagina-inicial")
    public String homePage() {
        return "gerenciadores";
    }

    @GetMapping("/pagina-inicial1")
    public String homePage1() {
        return "pagina-inicial1"; // AGORA CORRETO
    }

    @GetMapping("/gerenciador-usuario")
    public String GerenciadorUsuario() {
        return "/usuarios/gerenciar-usuario"; // AGORA CORRETO
    }
// -----------------------------------------

    // ------------- PAGINAS LOGIN ----------------
    @GetMapping("/login")
    public String login() {
        return "login"; // JÁ ESTAVA CORRETO
    }
// ----------------------------------------

// -------- PAGINAS DE EPI (CRUD) -------------------
    // OS MÉTODOS DE EPI FORAM REMOVIDOS DAQUI E MOVIDOS PARA EpiController.java
// -----------------------------------

}