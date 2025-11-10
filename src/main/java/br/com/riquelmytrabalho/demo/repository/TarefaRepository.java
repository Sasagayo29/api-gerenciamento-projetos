package br.com.riquelmytrabalho.demo.repository;

import br.com.riquelmytrabalho.demo.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    // Método que já tínhamos:
    // "SELECT * FROM tarefas WHERE projeto_id = ?"
    List<Tarefa> findByProjetoId(Long projetoId);

    /**
     * NOVO MÉTODO: Busca tarefas por ID do projeto E que contenham
     * a string de descrição.
     * Mapeia para: GET /api/projetos/{id}/tarefas/{nomeDaTarefa}
     *
     * @param projetoId O ID do projeto pai.
     * @param descricao A string de busca para a descrição da tarefa (ex: "Login")
     * @return Uma lista de tarefas que correspondem a ambos os critérios.
     */
    List<Tarefa> findByProjetoIdAndDescricaoContaining(Long projetoId, String descricao);
}
