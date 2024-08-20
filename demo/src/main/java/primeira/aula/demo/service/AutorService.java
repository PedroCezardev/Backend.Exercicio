package primeira.aula.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import primeira.aula.demo.exception.InvalidAutorException;
import primeira.aula.demo.model.Autor;
import primeira.aula.demo.repository.AutorRepository;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    public Autor inserirAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    public void deletarById(Long id){
        if (!autorRepository.existsById(id)) {
            throw new InvalidAutorException("O autor com este Id não foi encontrado no sistema.");
        }
        autorRepository.deleteById(id);
    }

    public List<Autor> retornarTodosAutores() {
        return autorRepository.findAll();
    }

    public Autor buscarPeloCpf(String cpf) {
        return autorRepository.findByCpf(cpf);
    }

    public List<Autor> buscarPelaIdade(Short idade) {
        return autorRepository.findByIdade(idade);
    }

}
