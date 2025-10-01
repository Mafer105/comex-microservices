package br.com.alura.comex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.comex.dto.ProdutoRequest;
import br.com.alura.comex.dto.ProdutoResponse;
import br.com.alura.comex.exception.ResourceNotFoundException;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.repository.CategoriaRepository;
import br.com.alura.comex.repository.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository; 

    public List<ProdutoResponse> getAll() {
    return produtoRepository.findAll().stream()
            .map(ProdutoResponse::new).toList();
}

    @Transactional
    public ProdutoResponse cadastrar(ProdutoRequest request) {
         Categoria categoria = categoriaRepository.findById(request.idCategoria())
            .orElseThrow(() -> new ResourceNotFoundException("Categoria com ID " + request.idCategoria() + " não encontrada!"));
        Produto novoProduto = new Produto();

        novoProduto.setNome(request.nome());
        novoProduto.setDescricao(request.descricao());
        novoProduto.setPreco(request.preco());
        novoProduto.setQntdEstoque(request.qntdEstoque());
        novoProduto.getCategorias().add(categoria);
        Produto produtoSalvo = produtoRepository.save(novoProduto);

        return new ProdutoResponse(produtoSalvo);
    }
}
