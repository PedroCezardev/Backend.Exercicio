package primeira.aula.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import primeira.aula.demo.exception.InvalidEditoraException;
import primeira.aula.demo.model.Editora;
import primeira.aula.demo.repository.EditoraRepository;

@Service
public class EditoraService {

    @Autowired
    private EditoraRepository editoraRepository;

    public Editora insertEditora(Editora editora) {
        validateEditora(editora);
        return editoraRepository.save(editora);
    }

    public List<Editora> getAllEditoras() {
        List<Editora> editoras = editoraRepository.findAll();
        if (editoras.isEmpty()) {
            throw new InvalidEditoraException("Nenhuma editora encontada.");
        }
        return editoraRepository.findAll();
    }

    public Editora getByCnpj(String cnpj) {
        Optional<Editora> editora = Optional.ofNullable(editoraRepository.findByCnpj(cnpj));
        return editora.orElseThrow(() -> 
            new InvalidEditoraException("A editora com CNPJ: " + cnpj + " não foi encontrada."));
            
    }

    public List<Editora> getByName(String nome) {
        List<Editora> editoras = editoraRepository.findByNome(nome);
        if (editoras.isEmpty()) {
            throw new InvalidEditoraException("A editora não foi encontrada com o Nome: " + nome);
        }
        return editoras;
    }

    public void deleteById(Long id){
        if (!editoraRepository.existsById(id)) {
            throw new InvalidEditoraException("A editora com este Id não foi encontrado no sistema.");
        }
        editoraRepository.deleteById(id);
        System.out.println("A Editora foi deletada com sucesso!");
    }

    public Editora updateEditoraById(Long id, Editora editoraDetails) {
        Optional<Editora> optionalEditora = editoraRepository.findById(id);
        if (optionalEditora.isEmpty()) {
            throw new InvalidEditoraException("Editora não encontrada com o id: " + id);
        }

        validateEditora(editoraDetails);
        Editora editora = optionalEditora.get();

        editora.setNome(editoraDetails.getNome());

        Editora updateEditora = editoraRepository.save(editora);
        return updateEditora;
    }

    private void validateEditora(Editora editora) {
        if ( editora.getNome().isEmpty() || editora.getNome().length() > 50 ) {
            throw new InvalidEditoraException("O nome da Editora não pode ser nulo, vazio ou ter mais de 50 caracteres");
        }
    }
}
