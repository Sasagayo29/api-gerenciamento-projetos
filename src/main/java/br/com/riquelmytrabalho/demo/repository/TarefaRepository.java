package br.com.riquelmytrabalho.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.riquelmytrabalho.demo.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    // --- Método de Consulta Customizado ---
    //
    // Precisamos de um endpoint para "Listar todas as tarefas de um projeto específico".
    // O Spring Data JPA nos permite criar métodos de consulta apenas nomeando-os corretamente.
    //
    // "findBy" -> Prefixo de busca
    // "Projeto" -> O atributo na entidade Tarefa (private Projeto projeto;)
    // "Id" -> O atributo aninhado (o 'id' dentro de 'Projeto')
    //
    // O Spring irá gerar o SQL: "SELECT * FROM tarefas WHERE projeto_id = ?"
    List<Tarefa> findByProjetoId(Long projetoId);
}