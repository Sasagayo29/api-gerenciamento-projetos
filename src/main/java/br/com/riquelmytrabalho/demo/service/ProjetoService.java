package br.com.riquelmytrabalho.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.riquelmytrabalho.demo.exception.ResourceNotFoundException;
import br.com.riquelmytrabalho.demo.model.Projeto;
import br.com.riquelmytrabalho.demo.repository.ProjetoRepository;

@Service // Marca esta classe como um Componente de Serviço do Spring
public class ProjetoService {

    @Autowired // Injeta (dá acesso) ao nosso repositório de projetos
    private ProjetoRepository projetoRepository;

    /**
     * Lista todos os projetos.
     */
    public List<Projeto> listarTodos() {
        return projetoRepository.findAll();
    }

    /**
     * Busca um projeto específico pelo ID.
     * Lança ResourceNotFoundException se não encontrar.
     */
    public Projeto buscarPorId(Long id) {
        // findById retorna um Optional.
        // .orElseThrow() é a forma elegante de:
        // 1. Pegar o Projeto se ele existir.
        // 2. Lançar a exceção se ele não existir.
        return projetoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado com o ID: " + id));
    }

    /**
     * Cria um novo projeto.
     * A data de criação é tratada pela entidade (@PrePersist).
     */
    public Projeto criar(Projeto projeto) {
        // Aqui poderíamos adicionar lógicas extras, ex:
        // if (projetoRepository.existsByNome(projeto.getNome())) { ... }
        return projetoRepository.save(projeto);
    }

    /**
     * Atualiza um projeto existente.
     */
    public Projeto atualizar(Long id, Projeto projetoDetalhes) {
        // 1. Primeiro, verifica se o projeto existe.
        // (O próprio buscarPorId já lança a exceção 404 se não existir)
        Projeto projetoExistente = buscarPorId(id);

        // 2. Atualiza os campos do projeto existente com os novos detalhes
        projetoExistente.setNome(projetoDetalhes.getNome());
        projetoExistente.setDescricao(projetoDetalhes.getDescricao());
        // Não atualizamos a dataCriacao nem o ID.

        // 3. Salva o projeto atualizado
        return projetoRepository.save(projetoExistente);
    }

    /**
     * Deleta um projeto pelo ID.
     */
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