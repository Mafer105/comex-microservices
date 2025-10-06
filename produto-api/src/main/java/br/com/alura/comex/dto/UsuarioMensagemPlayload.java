package br.com.alura.comex.dto;

public record UsuarioMensagemPlayload(String token,
    String nome,
    boolean ativo,
    TipoMensagemEnum tipo) {
    
}
