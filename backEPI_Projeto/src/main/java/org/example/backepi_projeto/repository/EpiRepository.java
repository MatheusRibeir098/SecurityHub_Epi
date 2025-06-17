package org.example.backepi_projeto.repository;

import org.example.backepi_projeto.model.EPI;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpiRepository extends JpaRepository<EPI, Long> {
}
