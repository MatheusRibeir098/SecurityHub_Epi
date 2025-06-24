package org.example.backepi_projeto.controller;

import org.example.backepi_projeto.model.EPI;
import org.example.backepi_projeto.repository.EpiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/epis")
public class EpiController {

    @Autowired
    private EpiRepository repository;

    @GetMapping
    public List<EPI> listar() {
        return repository.findAll();
    }

    @PostMapping
    public EPI salvar(EPI epi) {
        return repository.save(epi);
    }
}
