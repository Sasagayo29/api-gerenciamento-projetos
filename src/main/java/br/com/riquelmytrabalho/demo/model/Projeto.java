package br.com.riquelmytrabalho.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank; // Importa de 'jakarta'
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data // Anotação do Lombok (cria Getters, Setters, equals, hashCode, toString)
@NoArgsConstructor // Lombok (cria construtor vazio)
@AllArgsConstructor // Lombok (cria construtor com todos os campos)
@Entity // Marca esta classe como uma entidade JPA
@Table(name = "projetos") // Define o nome da tabela no DB
public class Projeto {

    @Id // Chave Primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremento
    private Long id;

    @NotBlank(message = "O nome do projeto é obrigatório") // Validação
    @Column(nullable = false) // Mapeamento da coluna (não pode ser nula)
    private String nome;

    private String descricao;

    @Column(nullable = false, updatable = false) // Não pode ser nulo, não pode ser atualizado
    private LocalDate dataCriacao;

    // Relacionamento Um-para-Muitos
    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference // Evita loop infinito ao serializar para JSON (este é o "pai")
    private List<Tarefa> tarefas;

    @PrePersist // Método executado antes de salvar um novo projeto
    protected void onCreate() {
        dataCriacao = LocalDate.now();
    }
}