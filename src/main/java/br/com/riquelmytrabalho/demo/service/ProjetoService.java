package br.com.riquelmytrabalho.demo.service;

import br.com.riquelmytrabalho.demo.exception.ResourceNotFoundException;
import br.com.riquelmytrabalho.demo.model.Projeto;
import br.com.riquelmytrabalho.demo.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    public List<Projeto> listarTodos() {
        return projetoRepository.findAll();
    }


    public Projeto buscarPorId(Long id) {

        return projetoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado com o ID: " + id));
    }

    /**
     * NOVO MÉTODO: Busca projetos por nome (busca parcial).
     */
    public List<Projeto> buscarPorNome(String nome) {
        // Se nada for encontrado, retorna uma lista vazia (o que é o correto para buscas)
        return projetoRepository.findByNomeContaining(nome);
    }

    public Projeto criar(Projeto projeto) {

        return projetoRepository.save(projeto);
    }

    public Projeto atualizar(Long id, Projeto projetoDetalhes) {

        Projeto projetoExistente = buscarPorId(id);

        projetoExistente.setNome(projetoDetalhes.getNome());
        projetoExistente.setDescricao(projetoDetalhes.getDescricao());

        return projetoRepository.save(projetoExistente);
    }

    public void deletar(Long id) {
        // 1. Verifica se o projeto existe antes de deletar.
        // (O próprio buscarPorId já lança a exceção 404 se não existir)
        Projeto projetoExistente = buscarPorId(id);

        // 2. Deleta o projeto.
        // Graças ao "CascadeType.ALL" na entidade Projeto,
        // todas as tarefas associadas a ele também serão deletadas.
        projetoRepository.delete(projetoExistente);
    }
}
