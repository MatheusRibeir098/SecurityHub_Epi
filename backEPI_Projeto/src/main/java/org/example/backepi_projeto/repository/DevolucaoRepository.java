package org.example.backepi_projeto.repository;

import org.example.backepi_projeto.model.Devolucao;
import org.example.backepi_projeto.model.Emprestimo; // Importar Emprestimo
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional; // Importar Optional

public interface DevolucaoRepository extends JpaRepository<Devolucao, Long> {

    // NOVO MÉTODO: Busca uma Devolucao pelo Emprestimo associado
    Optional<Devolucao> findByEmprestimo(Emprestimo emprestimo);

    // Você pode adicionar outros métodos de consulta personalizados aqui futuramente
}