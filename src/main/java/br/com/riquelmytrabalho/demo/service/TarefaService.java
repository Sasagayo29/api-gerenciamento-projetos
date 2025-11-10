package br.com.riquelmytrabalho.demo.service;

import br.com.riquelmytrabalho.demo.exception.ResourceNotFoundException;
import br.com.riquelmytrabalho.demo.model.Projeto;
import br.com.riquelmytrabalho.demo.model.Tarefa;
import br.com.riquelmytrabalho.demo.repository.ProjetoRepository;
import br.com.riquelmytrabalho.demo.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired // Precisamos dele para associar a tarefa a um projeto
    private ProjetoRepository projetoRepository;

    /**
     * Lista todas as tarefas.
     */
    public List<Tarefa> listarTodas() {
        return tarefaRepository.findAll();
    }

    /**
     * Busca uma tarefa específica pelo ID.
     */
    public Tarefa buscarPorId(Long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada com o ID: " + id));
    }

    /**
     * Busca todas as tarefas de um projeto específico.
     * (Implementa o requisito: GET /api/projetos/{id}/tarefas)
     */
    public List<Tarefa> buscarPorProjetoId(Long projetoId) {
        // 1. Verifica se o projeto pai existe
        if (!projetoRepository.existsById(projetoId)) {
            throw new ResourceNotFoundException("Projeto não encontrado com o ID: " + projetoId);
        }
        // 2. Usa o método customizado do nosso TarefaRepository
        return tarefaRepository.findByProjetoId(projetoId);
    }

    /**
     * NOVO MÉTODO: Busca tarefas por ID do projeto e descrição (busca parcial).
     */
    public List<Tarefa> buscarPorProjetoIdEDescricao(Long projetoId, String descricao) {
        // 1. Reutilizamos a boa prática de verificar se o projeto pai existe
        if (!projetoRepository.existsById(projetoId)) {
            throw new ResourceNotFoundException("Projeto não encontrado com o ID: " + projetoId);
        }
        
        // 2. Chama o novo método do repositório
        return tarefaRepository.findByProjetoIdAndDescricaoContaining(projetoId, descricao);
    }


    /**
     * Cria uma nova tarefa, associando-a a um projeto existente.
     */
    public Tarefa criar(Tarefa tarefa) {
        // O JSON da requisição virá com algo como: "projeto": { "id": 1 }
        Long projetoId = tarefa.getProjeto().getId();
        Projeto projeto = projetoRepository.findById(projetoId)
                .orElseThrow(() -> new ResourceNotFoundException("Não é possível criar a tarefa. Projeto não encontrado com o ID: " + projetoId));

        // Associa a entidade 'Projeto' completa e gerenciada pelo JPA
        tarefa.setProjeto(projeto);
        return tarefaRepository.save(tarefa);
    }

    /**
     * Atualiza uma tarefa existente.
     */
    public Tarefa atualizar(Long id, Tarefa tarefaDetalhes) {
        // 1. Busca a tarefa existente (ou lança 404)
        Tarefa tarefaExistente = buscarPorId(id);

        // 2. Atualiza os campos
        tarefaExistente.setDescricao(tarefaDetalhes.getDescricao());
        tarefaExistente.setStatus(tarefaDetalhes.getStatus());
        tarefaExistente.setDataLimite(tarefaDetalhes.getDataLimite());

        // 3. (Opcional) Permite mudar a tarefa de projeto
        if (tarefaDetalhes.getProjeto() != null && tarefaDetalhes.getProjeto().getId() != null) {
            Long novoProjetoId = tarefaDetalhes.getProjeto().getId();
            Projeto novoProjeto = projetoRepository.findById(novoProjetoId)
                    .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado com o ID: " + novoProjetoId));
            tarefaExistente.setProjeto(novoProjeto);
        }

        // 4. Salva as alterações
        return tarefaRepository.save(tarefaExistente);
    }

    /**
     * Deleta uma tarefa pelo ID.
     */
    public void deletar(Long id) {
        // 1. Verifica se a tarefa existe (ou lança 404)
        Tarefa tarefaExistente = buscarPorId(id);

        // 2. Deleta a tarefa
        tarefaRepository.delete(tarefaExistente);
    }
}
