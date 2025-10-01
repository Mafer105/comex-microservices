package br.com.alura.comex.dto;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.StatusCategoria;

public record CategoriaResponse(
    String nome, 
    StatusCategoria status
) {
    public CategoriaResponse(Categoria categoria) {
        this(categoria.getNome(), categoria.getStatus());
    }
}