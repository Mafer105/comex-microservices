package br.com.alura.comex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.alura.comex.dto.UsuarioPostRequest;
import br.com.alura.comex.model.Usuario;
import br.com.alura.comex.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username);
    }
    
    @Transactional
    public Usuario cadastrar(UsuarioPostRequest dados) {
        if (usuarioRepository.existsByEmail(dados.email())) {
            throw new IllegalStateException("E-mail já cadastrado.");
        }
        var usuario = new Usuario();
        usuario.setEmail(dados.email());
        usuario.setSenha(passwordEncoder.encode(dados.senha()));
        
        return usuarioRepository.save(usuario);
    }
}
