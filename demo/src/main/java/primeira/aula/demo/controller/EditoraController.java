package primeira.aula.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import primeira.aula.demo.model.Editora;
import primeira.aula.demo.service.EditoraService;



@RestController 
@RequestMapping(value = "/api/livro")
public class EditoraController{

    @Autowired
    public EditoraService editoraService;

    @PostMapping("/add")
    public Editora inserteEditora(@RequestBody Editora editora){
        return editoraService.inserirEditora(editora);
    }

    @GetMapping("/all")
    public List<Editora> buscarTodasEditoras(){
        return editoraService.listarTodasEditoras();
    }

    @GetMapping("/cnpj/{cpnj}")
    public Editora buscarPeloCnpj(@PathVariable String cnpj){
        return editoraService.buscarPeloCnpj(cnpj);
    }

    @GetMapping("/nome{nome}")
    public List<Editora> buscarPeloNome(@PathVariable String nome){
        return editoraService.buscarPeloNome(nome);
    }

}