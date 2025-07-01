package org.example.backepi_projeto.repository;

import org.example.backepi_projeto.model.Emprestimo;
import org.example.backepi_projeto.model.User; // Importar o modelo User
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // Importar a anotação Query
import java.util.List; // Importar List

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    /**
     * Busca todos os empréstimos pendentes (não devolvidos) para um usuário específico.
     * Um empréstimo é considerado pendente se não houver um registro correspondente na tabela Devolucao.
     * @param user O objeto User para o qual buscar os empréstimos.
     * @return Uma lista de objetos Emprestimo pendentes para o usuário.
     */
    @Query("SELECT e FROM Emprestimo e WHERE e.usuario = :user AND NOT EXISTS (" +
            "  SELECT d FROM Devolucao d WHERE d.emprestimo = e" +
            ")")
    List<Emprestimo> findPendingLoansByUser(User user);

    // Você pode adicionar outros métodos de consulta personalizados aqui, se necessário
}