package primeira.aula.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import primeira.aula.demo.exception.InvalidAutorException;
import primeira.aula.demo.model.Autor;
import primeira.aula.demo.service.AutorService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController 
@RequestMapping(value = "/api/autor")
public class AutorController{
    
    // importando autorservice
    @Autowired
    private AutorService autorService;

    // testando conexão com api
    @GetMapping("/teste")
    public ResponseEntity<String> teste() {
        return ResponseEntity.ok("Teste funcionando");
    }

    @PostMapping("/add")
    public Autor inserirAutor(@RequestBody Autor autor){
        try {
            return autorService.insertAutor(autor);
        } catch (InvalidAutorException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/all")
    public List<Autor> getTodosAutores(){
        try {
            return autorService.getAllAutores();
        } catch (InvalidAutorException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    @RequestMapping("/cpf/{cpf}")
    public Autor getByCpfAutor(@PathVariable String cpf){
        try {
            return autorService.getByCpf(cpf);
        } catch (InvalidAutorException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    @RequestMapping("/idade/{idade}")
    public List<Autor> getByIdadeAutor(@PathVariable Short idade){
        try {
            return autorService.getByIdade(idade);
        } catch (InvalidAutorException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/nome/{nome}")
    public List<Autor> getNameAutor(@PathVariable String nome){
        try {
            return autorService.getByName(nome);
        } catch (InvalidAutorException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteAutor(@PathVariable Long id){
        try {
            autorService.deleteById(id); 
        } catch (InvalidAutorException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public Autor updateAutor(@PathVariable Long id, Autor autorDetails) {
        try {
            return autorService.updateAutorById(id, autorDetails);
        } catch (InvalidAutorException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
 
}