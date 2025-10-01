package br.com.alura.comex.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.Usuario;
import br.com.alura.comex.model.Perfil;
import br.com.alura.comex.repository.ClienteRepository;
import br.com.alura.comex.dto.ClientePostRequest;


@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    @Transactional
    public Cliente cadastrar(ClientePostRequest dados) {
        if (clienteRepository.findByCpf(dados.cpf()).isPresent()) {
            throw new IllegalStateException("CPF já cadastrado.");
        }
        if (clienteRepository.findByEmail(dados.email()).isPresent()) {
            throw new IllegalStateException("E-mail já cadastrado.");
        }
        var usuario = new Usuario();
        usuario.setEmail(dados.email());
        usuario.setSenha(passwordEncoder.encode(dados.senha()));
        usuario.setPerfil(Perfil.ROLE_CLIENTE);

        var cliente = new Cliente();
        cliente.setNome(dados.nome());
        cliente.setCpf(dados.cpf());
        cliente.setEmail(dados.email());
        cliente.setTelefone(dados.telefone());

        cliente.setUsuario(usuario);
        return clienteRepository.save(cliente);
    }
}
