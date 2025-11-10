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

    @Autowired
    private ProjetoRepository projetoRepository;

    public List<Tarefa> listarTodas() {
        return tarefaRepository.findAll();
    }


    public Tarefa buscarPorId(Long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada com o ID: " + id));
    }

    public List<Tarefa> buscarPorProjetoId(Long projetoId) {
        if (!projetoRepository.existsById(projetoId)) {
            throw new ResourceNotFoundException("Projeto não encontrado com o ID: " + projetoId);
        }
        return tarefaRepository.findByProjetoId(projetoId);
    }

    /**
     * NOVO MÉTODO: Busca tarefas por ID do projeto e descrição (busca parcial).
     */
    public List<Tarefa> buscarPorProjetoIdEDescricao(Long projetoId, String descricao) {
        if (!projetoRepository.existsById(projetoId)) {
            throw new ResourceNotFoundException("Projeto não encontrado com o ID: " + projetoId);
        }
        
        return tarefaRepository.findByProjetoIdAndDescricaoContaining(projetoId, descricao);
    }

    public Tarefa criar(Tarefa tarefa) {
        Long projetoId = tarefa.getProjeto().getId();
        Projeto projeto = projetoRepository.findById(projetoId)
                .orElseThrow(() -> new ResourceNotFoundException("Não é possível criar a tarefa. Projeto não encontrado com o ID: " + projetoId));

        tarefa.setProjeto(projeto);
        return tarefaRepository.save(tarefa);
    }

    public Tarefa atualizar(Long id, Tarefa tarefaDetalhes) {
        Tarefa tarefaExistente = buscarPorId(id);

        tarefaExistente.setDescricao(tarefaDetalhes.getDescricao());
        tarefaExistente.setStatus(tarefaDetalhes.getStatus());
        tarefaExistente.setDataLimite(tarefaDetalhes.getDataLimite());

        if (tarefaDetalhes.getProjeto() != null && tarefaDetalhes.getProjeto().getId() != null) {
            Long novoProjetoId = tarefaDetalhes.getProjeto().getId();
            Projeto novoProjeto = projetoRepository.findById(novoProjetoId)
                    .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado com o ID: " + novoProjetoId));
            tarefaExistente.setProjeto(novoProjeto);
        }

        return tarefaRepository.save(tarefaExistente);
    }

    public void deletar(Long id) {
        Tarefa tarefaExistente = buscarPorId(id);

        tarefaRepository.delete(tarefaExistente);
    }
}
