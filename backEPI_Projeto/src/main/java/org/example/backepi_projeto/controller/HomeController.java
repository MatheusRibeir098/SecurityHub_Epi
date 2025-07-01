package org.example.backepi_projeto.controller;

import org.example.backepi_projeto.repository.EpiRepository; // Pode remover esta importação se não for mais usada
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private EpiRepository epiRepository;

    //----------------- PAGINA INICIAL ----------
    @GetMapping("/pagina-inicial")
    public String homePage() {
        return "gerenciadores";
    }

    @GetMapping("/pagina-inicial-usuario")
    public String homePageUsuario() {
        return "/gerenciadores-usuario/tela-principal-usuario";
    }

    @GetMapping("/SecurityHub")
    public String homePage1() {
        return "Sobre-SecurityHub"; // AGORA CORRETO
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