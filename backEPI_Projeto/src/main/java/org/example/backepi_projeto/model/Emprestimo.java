package org.example.backepi_projeto.model;

import jakarta.persistence.*; // Importe todas as anotações JPA
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime; // Para mapear tipos DATETIME do MySQL

@Entity // Marca esta classe como uma entidade JPA
@Table(name = "emprestimo") // Mapeia para a tabela 'emprestimo' no banco de dados
@Getter // Gera getters Lombok
@Setter // Gera setters Lombok
@NoArgsConstructor // Construtor sem argumentos Lombok
@AllArgsConstructor // Construtor com todos os argumentos Lombok
public class Emprestimo {

    @Id // Marca como chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Geração automática de ID pelo banco de dados
    @Column(name = "id_emprestimo") // Mapeia para a coluna 'id_emprestimo'
    private Long id;

    // Relacionamento Many-to-One com Usuario
    @ManyToOne(fetch = FetchType.LAZY) // Muitos empréstimos para um usuário
    @JoinColumn(name = "id_usuario", nullable = false) // Coluna de chave estrangeira
    private User usuario;

    // Relacionamento Many-to-One com EPI
    @ManyToOne(fetch = FetchType.LAZY) // Muitos empréstimos para um EPI
    @JoinColumn(name = "id_epi", nullable = false) // Coluna de chave estrangeira
    private EPI epi;

    @Column(name = "data_retirada", nullable = false) // Mapeia para a coluna 'data_retirada'
    private LocalDateTime dataRetirada;

    @Column(name = "data_prevista_devolucao", nullable = false) // Mapeia para 'data_prevista_devolucao'
    private LocalDateTime dataPrevistaDevolucao;

    @Column(name = "confirmacao_retirada", nullable = false) // Mapeia para 'confirmacao_retirada'
    private boolean confirmacaoRetirada; // tinyint(1) geralmente mapeia para boolean
}