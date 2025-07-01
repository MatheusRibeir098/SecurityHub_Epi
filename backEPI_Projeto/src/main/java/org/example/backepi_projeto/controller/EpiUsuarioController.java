package org.example.backepi_projeto.controller;

import org.example.backepi_projeto.model.EPI;
import org.example.backepi_projeto.model.Emprestimo;
import org.example.backepi_projeto.model.Devolucao;
import org.example.backepi_projeto.model.User;
import org.example.backepi_projeto.repository.EpiRepository;
import org.example.backepi_projeto.repository.EmprestimoRepository;
import org.example.backepi_projeto.repository.DevolucaoRepository;
import org.example.backepi_projeto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/gerenciadores-usuario")
public class EpiUsuarioController {

    @Autowired
    private EpiRepository epiRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private DevolucaoRepository devolucaoRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/epis-disponiveis")
    public String showEpisDisponiveis(Model model) {
        List<EPI> episDisponiveis = epiRepository.findAvailableEpis();
        model.addAttribute("epis", episDisponiveis);
        // AJUSTADO: O nome do arquivo HTML para corresponder à nova estrutura de pastas
        return "gerenciadores-usuario/epis-disponiveis";
    }

    @PostMapping("/emprestar-epi/{epiId}")
    public String emprestarEpi(@PathVariable Long epiId,
                               @AuthenticationPrincipal UserDetails userDetails,
                               RedirectAttributes redirectAttributes) {

        try {
            String userEmail = userDetails.getUsername();
            User currentUser = userRepository.findByUsername(userEmail)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + userEmail));

            EPI epi = epiRepository.findById(epiId)
                    .orElse(null);

            if (epi == null) {
                redirectAttributes.addFlashAttribute("mensagemErro", "EPI não encontrado.");
                return "redirect:/gerenciadores-usuario/epis-disponiveis";
            }

            if (epi.getQuantidade() <= 0) {
                redirectAttributes.addFlashAttribute("mensagemErro", "EPI não disponível para empréstimo no momento.");
                return "redirect:/gerenciadores-usuario/epis-disponiveis";
            }

            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setUsuario(currentUser);
            emprestimo.setEpi(epi);
            emprestimo.setDataRetirada(LocalDateTime.now());
            emprestimo.setDataPrevistaDevolucao(LocalDateTime.now().plusDays(30));
            emprestimo.setConfirmacaoRetirada(false);

            emprestimoRepository.save(emprestimo);

            epi.setQuantidade(epi.getQuantidade() - 1);
            epiRepository.save(epi);

            redirectAttributes.addFlashAttribute("mensagemSucesso", "EPI '" + epi.getNome() + "' emprestado com sucesso!");
            return "redirect:/gerenciadores-usuario/epis-disponiveis";
        } catch (UsernameNotFoundException e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro: Usuário autenticado não encontrado.");
            return "redirect:/login";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao emprestar EPI: " + e.getMessage());
            return "redirect:/gerenciadores-usuario/epis-disponiveis";
        }
    }

    @GetMapping("/meus-emprestimos")
    public String showMeusEmprestimos(@AuthenticationPrincipal UserDetails userDetails, Model model, RedirectAttributes redirectAttributes) {
        try {
            String userEmail = userDetails.getUsername();
            User currentUser = userRepository.findByUsername(userEmail)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + userEmail));

            List<Emprestimo> meusEmprestimosPendentes = emprestimoRepository.findPendingLoansByUser(currentUser);
            model.addAttribute("emprestimos", meusEmprestimosPendentes);

            if (model.containsAttribute("mensagemSucesso")) {
                model.addAttribute("mensagemSucesso", model.getAttribute("mensagemSucesso"));
            }
            if (model.containsAttribute("mensagemErro")) {
                model.addAttribute("mensagemErro", model.getAttribute("mensagemErro"));
            }

            // AJUSTADO: O nome do arquivo HTML para corresponder à nova estrutura de pastas
            return "gerenciadores-usuario/meus-emprestimos";
        } catch (UsernameNotFoundException e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro: Usuário autenticado não encontrado.");
            return "redirect:/login";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao carregar meus empréstimos: " + e.getMessage());
            // AJUSTADO: Redireciona para a nova página principal do usuário em caso de erro
            return "redirect:/gerenciadores-usuario/tela-principal-usuario";
        }
    }

    @PostMapping("/devolver-epi/{emprestimoId}")
    public String devolverEpi(@PathVariable Long emprestimoId,
                              RedirectAttributes redirectAttributes) {
        try {
            Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId)
                    .orElseThrow(() -> new IllegalArgumentException("Empréstimo não encontrado com ID: " + emprestimoId));

            if (devolucaoRepository.findByEmprestimo(emprestimo).isPresent()) {
                redirectAttributes.addFlashAttribute("mensagemErro", "Este EPI já foi devolvido.");
                return "redirect:/gerenciadores-usuario/meus-emprestimos";
            }

            Devolucao devolucao = new Devolucao();
            devolucao.setEmprestimo(emprestimo);
            devolucao.setDataDevolucao(LocalDateTime.now());
            devolucaoRepository.save(devolucao);

            EPI epi = emprestimo.getEpi();
            epi.setQuantidade(epi.getQuantidade() + 1);
            epiRepository.save(epi);

            redirectAttributes.addFlashAttribute("mensagemSucesso", "EPI '" + epi.getNome() + "' devolvido com sucesso!");
            return "redirect:/gerenciadores-usuario/meus-emprestimos";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("mensagemErro", e.getMessage());
            return "redirect:/gerenciadores-usuario/meus-emprestimos";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao devolver EPI: " + e.getMessage());
            return "redirect:/gerenciadores-usuario/meus-emprestimos";
        }
    }
}