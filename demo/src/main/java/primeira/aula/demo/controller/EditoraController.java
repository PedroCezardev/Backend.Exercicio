package primeira.aula.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import primeira.aula.demo.exception.InvalidEditoraException;
import primeira.aula.demo.model.Editora;
import primeira.aula.demo.service.EditoraService;


@RestController 
@RequestMapping(value = "/api/editora")
public class EditoraController{

    @Autowired
    public EditoraService editoraService;

    @PostMapping("/add")
    public Editora insertEditora(@RequestBody Editora editora){
        try {
            return editoraService.insertEditora(editora);
        } catch (InvalidEditoraException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/all")
    public List<Editora> getAllEditoras(){
        try {
            return editoraService.getAllEditoras();
        } catch (InvalidEditoraException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/cnpj/{cnpj}")
    public Editora getCnpj(@PathVariable String cnpj){
        try {
            return editoraService.getByCnpj(cnpj);
        } catch (InvalidEditoraException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/nome/{nome}")
    public List<Editora> buscarPeloNome(@PathVariable String nome){
        try {
            return editoraService.getByName(nome);
        } catch (InvalidEditoraException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEditora(@PathVariable Long id){
        try {
            editoraService.deleteById(id);
        } catch (InvalidEditoraException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public Editora updateEditora(@PathVariable Long id, @RequestBody Editora editoraDetails) {
        try {
            return editoraService.updateEditoraById(id, editoraDetails);
        } catch (InvalidEditoraException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}