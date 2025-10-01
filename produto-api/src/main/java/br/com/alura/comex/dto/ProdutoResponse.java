package br.com.alura.comex.dto;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;
import java.util.List;

public record ProdutoResponse(
    Long id,
    String nome,
    Double preco,
    Integer qntdEstoque,
    List<String> categorias 
) {
    public ProdutoResponse(Produto produto) {
        this(
            produto.getId(),
            produto.getNome(),
            produto.getPreco(),
            produto.getQntdEstoque(),
            produto.getCategorias().stream()
                .map(Categoria::getNome).toList()
        );
    }
}