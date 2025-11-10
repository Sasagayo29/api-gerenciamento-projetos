package br.com.riquelmytrabalho.demo.repository;

import br.com.riquelmytrabalho.demo.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List; // Importar List

@Repository // Marca esta interface como um componente Spring (Repositório)
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    // JpaRepository<T, ID>
    // T = A entidade que este repositório gere (Projeto)
    // ID = O tipo da chave primária da entidade (Long)

    // O Spring Data JPA já nos fornece:
    // - save(Projeto projeto) -> Salva ou atualiza
    // - findById(Long id) -> Busca por ID
    // - findAll() -> Lista todos
    // - deleteById(Long id) -> Deleta por ID
    // - e muitos outros!

    /**
     * NOVO MÉTODO: Busca projetos cujo nome contenha a string fornecida.
     * Mapeia para: GET /api/projetos/nome/{nomeDoProjeto}
     *
     * @param nome A string de busca (ex: "Website")
     * @return Uma lista de projetos que correspondem à busca.
     */
    List<Projeto> findByNomeContaining(String nome);
}
