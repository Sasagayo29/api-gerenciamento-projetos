package br.com.riquelmytrabalho.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.riquelmytrabalho.demo.exception.ResourceNotFoundException;
import br.com.riquelmytrabalho.demo.model.Projeto;
import br.com.riquelmytrabalho.demo.repository.ProjetoRepository;

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

        Projeto projetoExistente = buscarPorId(id);

        projetoRepository.delete(projetoExistente);
    }
}
