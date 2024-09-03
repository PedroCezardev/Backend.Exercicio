package primeira.aula.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import primeira.aula.demo.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    public List<Livro> findByTitulo(String titulo);

    public List<Livro> findByGenero(String genero);

    public List<Livro> findByPaginas(Integer paginas);

    public boolean existsByTitulo(String titulo);

    public boolean existsByPagina(String pagina);

    public void deleteByTiTle(String titulo);

    public void deleteByPagina(String pagina);

}
