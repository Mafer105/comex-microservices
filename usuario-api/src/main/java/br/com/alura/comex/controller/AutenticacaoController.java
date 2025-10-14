package br.com.alura.comex.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.comex.dto.TokenJWT;
import br.com.alura.comex.dto.UsuarioLoginRequest;
import br.com.alura.comex.dto.UsuarioMensagePayload;
import br.com.alura.comex.model.TipoMensagemEnum;
import br.com.alura.comex.model.Usuario;
import br.com.alura.comex.repository.UsuarioRepository;
import br.com.alura.comex.security.TokenService;
import br.com.alura.comex.dto.TokenValidationRequest;
import br.com.alura.comex.dto.TokenValidationResponse; 
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    private final AuthenticationManager manager;
    private final TokenService tokenService;
    private final RabbitTemplate rabbitTemplate;
    private final UsuarioRepository usuarioRepository;
    private static final String EXCHANGE_USERS = "users-actions-exchange";
    private static final String RK_USERS_CREATED = "users.actions";

    public AutenticacaoController(AuthenticationManager manager, TokenService tokenService, RabbitTemplate rabbitTemplate, UsuarioRepository usuarioRepository) {
        this.manager = manager;
        this.tokenService = tokenService;
        this.rabbitTemplate = rabbitTemplate;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping()  
    public ResponseEntity login(@RequestBody @Valid UsuarioLoginRequest request) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(request.email(), request.senha());

        try {
            var authentication = manager.authenticate(authenticationToken);
            var usuario = (Usuario) authentication.getPrincipal();
            var token = tokenService.gerarToken(usuario);
            var payload = new UsuarioMensagePayload(token, usuario.getNome(), usuario.isAtivo(), TipoMensagemEnum.AUTH);
            rabbitTemplate.convertAndSend(EXCHANGE_USERS, RK_USERS_CREATED, payload);
            
            return ResponseEntity.ok(new TokenJWT(token));
        }catch (AuthenticationException e){
            System.out.println("Falha na autenticação: " + e.getMessage());

            var usuarioOptional = usuarioRepository.findByEmail(request.email());

            if (usuarioOptional.isPresent()) {
                var usuario = usuarioOptional.get();
                var payload = new UsuarioMensagePayload(null, usuario.getNome(), usuario.isAtivo(), TipoMensagemEnum.BLOCKED);
                rabbitTemplate.convertAndSend("user.authenticated", payload);
            }
            
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }

    }

    @PostMapping("/token/validate")
    public ResponseEntity<TokenValidationResponse> validateToken(@RequestBody TokenValidationRequest request) {
        try {
        tokenService.getSubject(request.token());
        return ResponseEntity.ok(new TokenValidationResponse(true));
    } catch (Exception e) {
        System.err.println("!!! Erro na validação do token: " + e.getClass().getName() + " - " + e.getMessage());
        return ResponseEntity.ok(new TokenValidationResponse(false));
    }
    }
    
}
