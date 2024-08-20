package primeira.aula.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import primeira.aula.demo.model.Editora;
@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long> {

    public Editora findByCnpj(String cnpj);

    public List<Editora> findByNome(String nome);
    
}
