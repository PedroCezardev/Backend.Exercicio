package primeira.aula.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import primeira.aula.demo.exception.InvalidLivroException;
import primeira.aula.demo.model.Livro;
import primeira.aula.demo.repository.LivroRepository;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public Livro inserirLivro(Livro livro) {
        validateLivro(livro);
        return livroRepository.save(livro);
    }

    public List<Livro> getAllLivros() {
        List<Livro> livros = livroRepository.findAll();
        if (livros.isEmpty()) {
            throw new InvalidLivroException("Nenhum livro encontrado.");
        }
        return livros;
    }

    public List<Livro> getByPaginas(Integer paginas) {
        List<Livro> Livros = livroRepository.findByPaginas(paginas);
        if (Livros.isEmpty()) {
            throw new InvalidLivroException("O livro não foi encontrado com a página: " + paginas);
        }
        return Livros;
    }

    public List<Livro> getByTitulo(String titulo) {
        List<Livro> livros = livroRepository.findByTitulo(titulo);
        if (livros.isEmpty()) {
            throw new InvalidLivroException("O livro não foi encontrado com o títulio: " + titulo);
        }
        return livros;
    }

    public List<Livro> getByGenero(String genero) {
        List<Livro> livros = livroRepository.findByGenero(genero);
        if (livros.isEmpty()) {
            throw new InvalidLivroException("O livro não foi encontrado no genero: " + genero);
        }
        return livros;
    }

    public void deleteById(Long id){
        if (!livroRepository.existsById(id)) {
            throw new InvalidLivroException("O livro com este Id não foi encontrado no sistema.");
        }
        livroRepository.deleteById(id);
        System.out.println("O Livro foi deletado com sucesso!");
    }

    @Transactional
    public void deleteTitulo(String titulo) {
        if (!livroRepository.existsByTitulo(titulo)) {
            throw new InvalidLivroException("O livro com este Título não foi encontrado no sistema.");
        }
        livroRepository.deleteByTitulo(titulo);
        System.out.println("O Livro foi deletado com sucesso!");
    }

    @Transactional
    public void deletePaginas(Integer paginas) {
        if (!livroRepository.existsByPaginas(paginas)) {
            throw new InvalidLivroException("O livro com este Título não foi encontrado no sistema.");
        }
        livroRepository.deleteByPaginas(paginas);
        System.out.println("O Livro foi deletado com sucesso!");
    }

    public Livro updateLivroById(Long id, Livro livroDetails) {

        if (livroDetails == null) {
            throw new InvalidLivroException("Os detalhes do livro não podem ser nulos.");
        }

        Optional<Livro> optionalLivro = livroRepository.findById(id);
        if (optionalLivro.isEmpty()) {
            throw new InvalidLivroException("Livro não encontrado com id: " + id);
        }

        validateLivro(livroDetails);
        Livro livro = optionalLivro.get();

        livro.setTitulo(livroDetails.getTitulo());
        livro.setGenero(livroDetails.getGenero());
        livro.setPaginas(livroDetails.getPaginas());

        Livro updatedLivro = livroRepository.save(livro);
        return updatedLivro;
    }

    public void validateLivro(Livro livro) {
        if ( livro.getTitulo().isEmpty() || livro.getTitulo().length() > 50) {
            throw new InvalidLivroException("O Titulo do Livro não pode ser nulo, vazio ou ter mais de 50 caracteres");
        }
        if ( livro.getGenero().isEmpty() || livro.getGenero().length() > 50) {
            throw new InvalidLivroException("O Genero do Livro não pode ser nulo, vazio ou ter mais de 50 caracteres");
        }
        if (livro.getPaginas() <= 0) {
            throw new InvalidLivroException("O número de páginas do Livro deve ser maior que zero");
    }
    }
    
}
