package primeira.aula.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import primeira.aula.demo.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    public List<Livro> findByTitulo(String titulo);

    public List<Livro> findByGenero(String genero);

    public List<Livro> findByPaginas(Integer paginas);

    public boolean existsByTitulo(String titulo);

    public boolean existsByPaginas(Integer paginas);

    @Modifying
    @Transactional
    @Query("DELETE FROM Livro l WHERE l.titulo = ?1")
    public void deleteByTitulo(String titulo);

    @Modifying
    @Transactional
    @Query("DELETE FROM Livro l WHERE l.paginas = ?1")
    public void deleteByPaginas(Integer paginas);

}
