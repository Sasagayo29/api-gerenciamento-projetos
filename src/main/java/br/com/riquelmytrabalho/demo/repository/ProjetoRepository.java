package br.com.riquelmytrabalho.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.riquelmytrabalho.demo.model.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}