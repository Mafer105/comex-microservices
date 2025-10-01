package br.com.alura.comex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.comex.dto.CategoriaRequest;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired 
    private CategoriaService categoriaService;
    
    @GetMapping
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        return new ResponseEntity<>(categoriaService.getAll(),HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Categoria> cadastrarCategoria(
        @RequestBody @Valid CategoriaRequest request, 
        UriComponentsBuilder uriBuilder) {
        
        Categoria novaCategoria = categoriaService.cadastrar(request);
       
        return new ResponseEntity<>(novaCategoria,HttpStatus.CREATED);
    }
}
