package br.com.alura.comex.amqp;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.alura.comex.dto.UsuarioMensagemPlayload;
import br.com.alura.comex.model.UsuarioCache;
import br.com.alura.comex.repository.UsuarioCacheRepository;
import br.com.alura.comex.security.TokenService;

@Component
public class UsuarioListener {  

    private final UsuarioCacheRepository repository;
    private final TokenService tokenService;

    public UsuarioListener(UsuarioCacheRepository repository,TokenService tokenService) {
        this.repository = repository;
        this.tokenService = tokenService;
    }

    @RabbitListener(queues = RabbitMQConfiguration.QUEUE_USERS_LOGIN)
    public void receiveMessage(UsuarioMensagemPlayload payload){

        System.out.println("=============================================");
        System.out.println("Mensagem Recebida!");
        System.out.println("Tipo: " + payload.tipo());
        System.out.println("Nome/Email: " + payload.nome());
        System.out.println("Status Ativo: " + payload.ativo());
        System.out.println("Token: " + payload.token());
        System.out.println("=============================================");
        
        String email = tokenService.getSubject(payload.token());

        UsuarioCache usuario = repository.findByEmail(email)
                                        .orElse(new UsuarioCache());

        usuario.setEmail(email);
        usuario.setNome(payload.nome());
        usuario.setToken(payload.token());
        usuario.setAtivo(payload.ativo());
        usuario.setUltimaAtualizacao(LocalDateTime.now());

        repository.save(usuario);
    }
}
