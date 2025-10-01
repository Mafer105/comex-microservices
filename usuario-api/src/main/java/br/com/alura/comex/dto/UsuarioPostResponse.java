package br.com.alura.comex.dto;

import br.com.alura.comex.model.Usuario;

public record UsuarioPostResponse(Long id, String email) {
    public UsuarioPostResponse(Usuario usuario) {
        this(usuario.getId(), usuario.getEmail());
    }
}
