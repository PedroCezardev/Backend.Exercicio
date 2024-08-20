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
        return autorService.inserirAutor(autor);
    }

    @GetMapping("/all")
    public List<Autor> getTodosAutores(){
        return autorService.retornarTodosAutores();
    }

    @GetMapping
    @RequestMapping("/cpf/{cpf}")
    public Autor pegarPeloCpf(@PathVariable String cpf){
        return autorService.buscarPeloCpf(cpf);
    }

    @GetMapping
    @RequestMapping("/idade/{idade}")
    public List<Autor> pegarPelaIdade(@PathVariable Short idade){
        return autorService.buscarPelaIdade(idade);
    }

    @DeleteMapping("/{id}")
    public void deletarAutor(@PathVariable Long id){
        try {
            autorService.deletarById(id); 
        } catch (InvalidAutorException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
 
}