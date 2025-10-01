package br.com.alura.comex.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.alura.comex.dto.ProdutoRequest;
import br.com.alura.comex.dto.ProdutoResponse;
import br.com.alura.comex.service.ProdutoService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    
    @Autowired 
    ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> getAllProdutos() {
        return new ResponseEntity<>(produtoService.getAll(), HttpStatus.OK);
    }
    

    @PostMapping
    public ResponseEntity<ProdutoResponse> cadastraProduto(@RequestBody ProdutoRequest request) {
        ProdutoResponse produto = produtoService.cadastrar(request);

        return new ResponseEntity<>(produto, HttpStatus.CREATED);
    }
    
}
