package br.com.riquelmytrabalho.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping; // Importante: vindo de 'jakarta'
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.riquelmytrabalho.demo.model.Projeto;
import br.com.riquelmytrabalho.demo.model.Tarefa;
import br.com.riquelmytrabalho.demo.service.ProjetoService;
import br.com.riquelmytrabalho.demo.service.TarefaService;
import jakarta.validation.Valid;

@RestController // Define que esta classe é um Controller REST
@RequestMapping("/api/projetos") // Define o prefixo da URL para todos os métodos
public class ProjetoController {

    @Autowired // Injeta o serviço de Projeto
    private ProjetoService projetoService;

    @Autowired // Injeta o serviço de Tarefa (necessário para o endpoint extra)
    private TarefaService tarefaService;

    /**
     * Endpoint: POST /api/projetos
     * Cria um novo projeto.
     * @Valid: Ativa a validação (ex: @NotBlank) na entidade Projeto
     */
    @PostMapping
    public ResponseEntity<Projeto> criarProjeto(@Valid @RequestBody Projeto projeto) {
        Projeto novoProjeto = projetoService.criar(projeto);
        // Retorna 201 Created + o objeto criado no corpo da resposta
        return new ResponseEntity<>(novoProjeto, HttpStatus.CREATED);
    }

    /**
     * Endpoint: GET /api/projetos
     * Lista todos os projetos.
     */
    @GetMapping
    public List<Projeto> listarProjetos() {
        // O Spring automaticamente retorna 200 OK
        return projetoService.listarTodos();
    }

    /**
     * Endpoint: GET /api/projetos/{id}
     * Busca um projeto específico pelo id.
     * @PathVariable: Pega o 'id' da URL e passa para o método
     */
    @GetMapping("/{id}")
    public ResponseEntity<Projeto> buscarProjetoPorId(@PathVariable Long id) {
        Projeto projeto = projetoService.buscarPorId(id);
        // Retorna 200 OK + o projeto encontrado
        return ResponseEntity.ok(projeto);
    }

    /**
     * Endpoint: PUT /api/projetos/{id}
     * Atualiza os dados de um projeto existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Projeto> atualizarProjeto(@PathVariable Long id, @Valid @RequestBody Projeto projetoDetalhes) {
        Projeto projetoAtualizado = projetoService.atualizar(id, projetoDetalhes);
        return ResponseEntity.ok(projetoAtualizado);
    }

    /**
     * Endpoint: DELETE /api/projetos/{id}
     * Remove um projeto.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProjeto(@PathVariable Long id) {
        projetoService.deletar(id);
        // Retorna 204 No Content (sucesso, mas sem corpo na resposta)
        return ResponseEntity.noContent().build();
    }

    // --- Endpoint de Consulta Adicional ---

    /**
     * Endpoint: GET /api/projetos/{id}/tarefas
     * Lista todas as tarefas de um projeto específico.
     */
    @GetMapping("/{id}/tarefas")
    public List<Tarefa> listarTarefasDoProjeto(@PathVariable Long id) {
        return tarefaService.buscarPorProjetoId(id);
    }
}