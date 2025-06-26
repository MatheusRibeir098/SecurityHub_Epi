package org.example.backepi_projeto.controller;

import org.example.backepi_projeto.model.EPI;
import org.example.backepi_projeto.repository.EpiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/epis")
public class EpiController {

    @Autowired
    private EpiRepository repository;


    @GetMapping("/listar")
    public String listarEpis(Model model) {
        List<EPI> epis = repository.findAll();
        model.addAttribute("epis", epis);
        return "epis/lista-epis";
    }

    @GetMapping("/cadastrar-epis")
    public String cadastrarEpisPage(Model model) {
        model.addAttribute("epi", new EPI());
        return "epis/cadastrar-epis";
    }

    @PostMapping("/excluir/{id}")
    public String excluirEpi(@PathVariable Long id) {
        Optional<EPI> epiOptional = repository.findById(id);
        if (epiOptional.isPresent()) {
            repository.delete(epiOptional.get());
        }
        return "redirect:/epis/listar";
    }

    // NOVO MÉTODO: Exibe o formulário de edição preenchido com os dados do EPI
    @GetMapping("/atualizar-epis/{id}") // Mapeamento para /epis/atualizar-epis/{id}
    public String atualizarEpisPage(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<EPI> epiOptional = repository.findById(id);
        if (epiOptional.isPresent()) {
            model.addAttribute("epi", epiOptional.get()); // Passa o EPI encontrado para o formulário
            return "epis/atualizar-epis"; // Retorna o template de edição
        } else {
            redirectAttributes.addFlashAttribute("mensagemErro", "EPI não encontrado para edição.");
            return "redirect:/epis/listar"; // Redireciona para a lista se o EPI não for encontrado
        }
    }

    // MÉTODO MODIFICADO: Salva (para novo) ou Atualiza (para existente) um EPI
    @PostMapping // Mapeamento para /epis (base do @RequestMapping)
    public String salvar(@ModelAttribute EPI epi, RedirectAttributes redirectAttributes) {
        String mensagem;
        if (epi.getId() != null) { // Se o ID não é nulo, é uma atualização
            Optional<EPI> existingEPI = repository.findById(epi.getId());
            if (existingEPI.isPresent()) {
                EPI epiToUpdate = existingEPI.get();
                epiToUpdate.setNome(epi.getNome());
                epiToUpdate.setQuantidade(epi.getQuantidade());
                repository.save(epiToUpdate);
                mensagem = "EPI atualizado com sucesso!";
            } else {
                mensagem = "Erro: EPI não encontrado para atualização!";
            }
        } else { // Se o ID é nulo, é um novo cadastro
            repository.save(epi);
            mensagem = "EPI cadastrado com sucesso!";
        }
        redirectAttributes.addFlashAttribute("mensagemSucesso", mensagem);
        return "redirect:/epis/listar"; // Redireciona para a lista após salvar/atualizar
    }
}