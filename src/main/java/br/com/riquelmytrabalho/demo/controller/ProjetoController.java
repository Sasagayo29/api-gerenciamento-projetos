package br.com.riquelmytrabalho.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<Projeto> criarProjeto(@Valid @RequestBody Projeto projeto) {
        Projeto novoProjeto = projetoService.criar(projeto);
        return new ResponseEntity<>(novoProjeto, HttpStatus.CREATED);

    @GetMapping
    public List<Projeto> listarProjetos() {
        return projetoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> buscarProjetoPorId(@PathVariable Long id) {
        Projeto projeto = projetoService.buscarPorId(id);
        return ResponseEntity.ok(projeto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projeto> atualizarProjeto(@PathVariable Long id, @Valid @RequestBody Projeto projetoDetalhes) {
        Projeto projetoAtualizado = projetoService.atualizar(id, projetoDetalhes);
        return ResponseEntity.ok(projetoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProjeto(@PathVariable Long id) {
        projetoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/tarefas")
    public List<Tarefa> listarTarefasDoProjeto(@PathVariable Long id) {
        return tarefaService.buscarPorProjetoId(id);
    }
}
}
