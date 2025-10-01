package br.com.alura.comex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.comex.dto.ClientePostRequest;
import br.com.alura.comex.dto.ClientePostResponse;
import br.com.alura.comex.service.ClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClieneteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClientePostResponse> cadastrar(@RequestBody @Valid ClientePostRequest dados, UriComponentsBuilder uriBuilder) {
        var cliente = clienteService.cadastrar(dados);
        
        var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
        
        return ResponseEntity.created(uri).body(new ClientePostResponse(cliente));
    }
}
