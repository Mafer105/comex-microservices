package br.com.alura.comex.dto;

import br.com.alura.comex.model.TipoMensagemEnum;

public record UsuarioMensagePayload(String token,
    String nome,
    boolean ativo,
    TipoMensagemEnum tipo) {
    
}
