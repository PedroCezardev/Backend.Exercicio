package primeira.aula.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import primeira.aula.demo.model.Editora;
import primeira.aula.demo.repository.EditoraRepository;

@Service
public class EditoraService {
    private EditoraRepository editoraRepository;

    public Editora inserirEditora(Editora editora) {
       return editoraRepository.save(editora);
    }

    public List<Editora> listarTodasEditoras() {
        return editoraRepository.findAll();
    }

    public Editora buscarPeloCnpj(String cnpj) {
        return editoraRepository.findByCnpj(cnpj);
    }

    public List<Editora> buscarPeloNome(String nome) {
        return editoraRepository.findByNome(nome);
    }
}
