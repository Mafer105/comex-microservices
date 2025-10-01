package br.com.alura.comex.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProdutoRequest( @NotNull @Size(min = 2) String nome,@NotNull  @Positive double preco, String descricao,@NotNull Integer qntdEstoque,@NotNull  Long idCategoria) {
    
}