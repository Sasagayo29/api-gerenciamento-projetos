package br.com.riquelmytrabalho.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.riquelmytrabalho.demo.model.Projeto;

@Repository // Marca esta interface como um componente Spring (Repositório)
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    // JpaRepository<T, ID>
    // T = A entidade que este repositório gerencia (Projeto)
    // ID = O tipo da chave primária da entidade (Long)

    // O Spring Data JPA já nos fornece:
    // - save(Projeto projeto) -> Salva ou atualiza
    // - findById(Long id) -> Busca por ID
    // - findAll() -> Lista todos
    // - deleteById(Long id) -> Deleta por ID
    // - e muitos outros!
}