package br.com.riquelmytrabalho.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tarefas")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A descrição da tarefa é obrigatória")
    @Column(nullable = false)
    private String descricao;

    @NotNull(message = "O status é obrigatório")
    @Enumerated(EnumType.STRING) // Grava o nome do Enum (ex: "PENDENTE") no DB
    @Column(nullable = false)
    private StatusTarefa status;

    private LocalDate dataLimite;

    // Relacionamento Muitos-para-Um
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projeto_id", nullable = false) // Define a coluna da FK
    @NotNull(message = "A tarefa deve estar associada a um projeto")
    @JsonBackReference // Evita loop infinito ao serializar para JSON (este é o "filho")
    private Projeto projeto;
}