package org.example.backepi_projeto.model;

import jakarta.persistence.*; // Importe todas as anotações JPA
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime; // Para mapear tipos DATETIME do MySQL

@Entity // Marca esta classe como uma entidade JPA
@Table(name = "devolucao") // Mapeia para a tabela 'devolucao' no banco de dados
@Getter // Gera getters Lombok
@Setter // Gera setters Lombok
@NoArgsConstructor // Construtor sem argumentos Lombok
@AllArgsConstructor // Construtor com todos os argumentos Lombok
public class Devolucao {

    @Id // Marca como chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Geração automática de ID pelo banco de dados
    @Column(name = "id_devolucao") // Mapeia para a coluna 'id_devolucao'
    private Long id;

    // Relacionamento One-to-One ou Many-to-One com Emprestimo
    // Assumindo que um emprestimo pode ter apenas uma devolucao registrada aqui.
    // Se um emprestimo puder ter múltiplas devoluções parciais, isso seria Many-to-One.
    // Pelo schema, id_emprestimo é uma FK, mas não há UNIQUE nele na tabela 'devolucao',
    // então pode ser que um emprestimo tenha múltiplas devoluções.
    // Para simplificar, vamos usar ManyToOne, que é mais flexível.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_emprestimo", nullable = false) // Coluna de chave estrangeira
    private Emprestimo emprestimo;

    @Column(name = "data_devolucao", nullable = false) // Mapeia para a coluna 'data_devolucao'
    private LocalDateTime dataDevolucao;
}