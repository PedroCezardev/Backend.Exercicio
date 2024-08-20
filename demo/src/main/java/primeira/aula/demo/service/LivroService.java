package primeira.aula.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import primeira.aula.demo.model.Livro;
import primeira.aula.demo.repository.LivroRepository;

@Service
public class LivroService {

    private LivroRepository livroRepository;

    public Livro inserirLivro(Livro livro) {
        return livroRepository.save(livro);
    }

    public List<Livro> listarTodosLivros() {
        return livroRepository.findAll();
    }

    public Livro buscarPeloTitulo(String titulo) {
        return livroRepository.findByTitulo(titulo);
    }

    public List<Livro> buscarPeloGenero(String genero) {
        return livroRepository.findByGenero(genero);
    }

}
