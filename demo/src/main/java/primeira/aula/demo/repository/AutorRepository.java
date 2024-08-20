package primeira.aula.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import primeira.aula.demo.model.Autor;

//Quando temos a anotação Repository e fazemos o extends JpaRepository, automaticamente temos acesso a métodos como: save, findAll, findById, delete e deleteById
@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    
    public Autor findByCpf(String cpf);
    
    public List<Autor> findByIdade(Short idade);
    
    public void deleteByCpf(String cpf);

}