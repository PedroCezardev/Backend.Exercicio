package primeira.aula.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import primeira.aula.demo.exception.InvalidLivroException;
import primeira.aula.demo.model.Livro;
import primeira.aula.demo.service.LivroService;

@RestController 
@RequestMapping(value = "/api/livro")
public class LivroController{
    
    @Autowired
    public LivroService livroService;

    @PostMapping("/add")
    public Livro inserirLivro(@RequestBody Livro livro){
        try {
            return livroService.inserirLivro(livro);
        } catch (InvalidLivroException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/all")
    public List<Livro> getByLivros(){
        try {
            return livroService.getAllLivros();
        } catch (InvalidLivroException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/paginas/{paginas}")
    public List<Livro> getByPaginasLivros(@PathVariable Integer paginas) {
        try {
            return livroService.getByPaginas(paginas);
        } catch (InvalidLivroException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    

    @GetMapping("/titulo/{titulo}")
    public List<Livro> getByTituloLivros(@PathVariable String titulo){
        try {
            return livroService.getByTitulo(titulo);
        } catch (InvalidLivroException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    @RequestMapping("/genero/{genero}")
    public List<Livro> getByGeneroLivro(@PathVariable String genero){
        try {
            return livroService.getByGenero(genero);
        } catch (InvalidLivroException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLivro(@PathVariable Long id) {
        try {
            livroService.deleteById(id);
        } catch (InvalidLivroException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/delete/titulo/{titulo}")
    public void deleteByTitulo(@PathVariable String titulo) {
        try {
            livroService.deleteTitulo(titulo);
        } catch (InvalidLivroException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/delete/paginas/{paginas}")
    public void deleteByPaginas(@PathVariable Integer paginas){
        try {
            livroService.deletePaginas(paginas);
        } catch (InvalidLivroException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateLivro(@PathVariable Long id, @RequestBody Livro livroDetails){
        try {
            Livro updatedLivro = livroService.updateLivroById(id, livroDetails);
            return ResponseEntity.ok(updatedLivro);
        } catch (InvalidLivroException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro inesperado.");
        }
    }
}