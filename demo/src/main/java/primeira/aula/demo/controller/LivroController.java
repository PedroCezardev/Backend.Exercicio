package primeira.aula.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import primeira.aula.demo.model.Livro;
import primeira.aula.demo.service.LivroService;


@RestController 
@RequestMapping(value = "/api/livro")
public class LivroController{
    
    @Autowired
    public LivroService livroService;

    @PostMapping("/add")
    public Livro inserirLivro(@RequestBody Livro livro){
        return livroService.inserirLivro(livro);
    }

    @GetMapping("/all")
    public List<Livro> getTodosLivros(){
        return livroService.listarTodosLivros();
    }

    @GetMapping("/titulo")
    public Livro getLivroTitulo(String titulo){
        return livroService.buscarPeloTitulo(titulo);
    }

    @GetMapping
    @RequestMapping("/genero")
    public List<Livro> buscarPeloGenero(String genero){
        return livroService.buscarPeloGenero(genero);
    }
}