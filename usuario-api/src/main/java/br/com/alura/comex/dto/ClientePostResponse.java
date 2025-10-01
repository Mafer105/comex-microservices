package br.com.alura.comex.dto;

import br.com.alura.comex.model.Cliente;

public record ClientePostResponse(Long id,
    String nome,
    String cpf,
    String email) {
    public ClientePostResponse(Cliente cliente){
        this(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getEmail());
    }
}
