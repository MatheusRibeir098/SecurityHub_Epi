package org.example.backepi_projeto.repository;

import org.example.backepi_projeto.model.EPI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface EpiRepository extends JpaRepository<EPI, Long> {

    // ALTERADO: A query foi simplificada para retornar EPIs apenas com base na quantidade disponível.
    // A lógica de empréstimo/devolução já atualiza a quantidade diretamente no estoque do EPI.
    @Query("SELECT epi FROM EPI epi WHERE epi.quantidade > 0")
    List<EPI> findAvailableEpis();
}