package br.com.alura.comex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.comex.dto.TokenJWT;
import br.com.alura.comex.dto.UsuarioLoginRequest;
import br.com.alura.comex.model.Usuario;
import br.com.alura.comex.security.TokenService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping()  
    public ResponseEntity login(@RequestBody @Valid UsuarioLoginRequest request) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(request.email(), request.senha());
        var authentication = manager.authenticate(authenticationToken);
        var token = tokenService.gerarToken((Usuario)authentication.getPrincipal());
        return ResponseEntity.ok(new TokenJWT(token));

    }
}
