package org.example.backepi_projeto.controller;

import org.example.backepi_projeto.model.EPI;
import org.example.backepi_projeto.repository.EpiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // MUDANÇA: de @RestController para @Controller
import org.springframework.ui.Model; // NOVA IMPORTAÇÃO
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional; // NOVA IMPORTAÇÃO

@Controller // MUDANÇA
@RequestMapping("/epis")
public class EpiController {

    @Autowired
    private EpiRepository repository;


    // MIGRADO DO HOMECONTROLLER: Exibe a lista de EPIs
    @GetMapping("/listar") // Mapeamento ajustado para /epis/listar
    public String listarEpis(Model model) {
        List<EPI> epis = repository.findAll();
        model.addAttribute("epis", epis);
        return "epis/lista-epis";
    }

    // MIGRADO DO HOMECONTROLLER: Exibe o formulário de cadastro de EPI
    @GetMapping("/cadastrar-epis") // Mapeamento ajustado para /epis/cadastrar-epis
    public String cadastrarEpisPage(Model model) { // Adicionado Model para passar o objeto EPI
        model.addAttribute("epi", new EPI()); // Prepara um novo objeto EPI para o formulário
        return "epis/cadastrar-epis";
    }

    // MIGRADO DO HOMECONTROLLER: Processa a exclusão de um EPI
    @PostMapping("/excluir/{id}") // Mapeamento ajustado para /epis/excluir/{id} (mantendo o que já funcionou)
    public String excluirEpi(@PathVariable Long id) {
        Optional<EPI> epiOptional = repository.findById(id);
        if (epiOptional.isPresent()) {
            repository.delete(epiOptional.get());
        }
        return "redirect:/epis/listar"; // Redireciona para a lista de EPIs
    }

    // Método para salvar um novo EPI (ajustado para formulário Thymeleaf)
    @PostMapping // Mapeamento para /epis (base do @RequestMapping)
    public String salvar(@ModelAttribute EPI epi, RedirectAttributes redirectAttributes) { // Alterado para receber via @ModelAttribute
        repository.save(epi);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Epi cadastrado com sucesso!");
        return "redirect:/epis/listar"; // Redireciona para a lista após salvar
    }
}