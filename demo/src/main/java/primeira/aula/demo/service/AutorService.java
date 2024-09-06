package primeira.aula.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import primeira.aula.demo.exception.InvalidAutorException;
import primeira.aula.demo.exception.InvalidLivroException;
import primeira.aula.demo.model.Autor;
import primeira.aula.demo.repository.AutorRepository;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    public Autor insertAutor(Autor autor) {
        // Validações para evitar inserção de dados inválidos
        validateAutor(autor);
        return autorRepository.save(autor);
    }

    public List<Autor> getAllAutores() {
        List<Autor> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            throw new InvalidAutorException("Nenhum autor encontrado.");
        }
        return autores;
    }

    public Autor getByCpf(String cpf) {
        Optional<Autor> autor = Optional.ofNullable(autorRepository.findByCpf(cpf));
        return autor.orElseThrow(() -> 
            new InvalidAutorException("O autor com CPF: " + cpf + " não foi encontrado."));
    }

    public List<Autor> getByIdade(Short idade) {
        List<Autor> autores = autorRepository.findByIdade(idade);
        if (autores.isEmpty()) {
            throw new InvalidAutorException("Nenhum autor encontrado com idade: " + idade);
        }
        return autores;
    }

    public List<Autor> getByName(String nome) {
        List<Autor> autores = autorRepository.findByNome(nome);
        if (autores.isEmpty()) {
            throw new InvalidAutorException("O autor não foi encontrado com nome: " + nome);
        }
        return autores;
    }

    public void deleteById(Long id){
        if (!autorRepository.existsById(id)) {
            throw new InvalidAutorException("O autor com este Id não foi encontrado no sistema.");
        }
        autorRepository.deleteById(id);
        System.out.println("O autor foi deletado com sucesso!");
    }

    public Autor updateAutorById(Long id, Autor autorDetails) {

        if (autorDetails == null) {
            throw new InvalidLivroException("Os detalhes do Autor não podem ser nulos.");
        }

        Optional<Autor> optionalAutor = autorRepository.findById(id);
        if (optionalAutor.isEmpty()) {
            throw new InvalidAutorException("Autor não encontrado com id: " + id);
        }

        validateAutor(autorDetails);
        Autor autor = optionalAutor.get();

        autor.setNome(autorDetails.getNome());
        autor.setIdade(autorDetails.getIdade());
        
        Autor updatedAutor = autorRepository.save(autor);
        return updatedAutor;
    }

    // Validações para verificar a integridade dos dados do Autor
    private void validateAutor(Autor autor) {
        if ( autor.getNome().isEmpty() || autor.getNome().length() > 50) {
            throw new InvalidAutorException("O nome do Autor não pode ser nulo ou vazio");
        }
        if (autor.getIdade() == null || autor.getIdade() <= 0) {
            throw new InvalidAutorException("A idade do Autor deve ser maior que zero");
        }
    }
    
}
