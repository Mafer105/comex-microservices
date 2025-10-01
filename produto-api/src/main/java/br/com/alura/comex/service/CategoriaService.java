package br.com.alura.comex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.comex.dto.CategoriaRequest;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.StatusCategoria;
import br.com.alura.comex.repository.CategoriaRepository;

@Service
public class CategoriaService {
     @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> getAll(){
        return categoriaRepository.findAll();
    }
    @Transactional
    public Categoria cadastrar(CategoriaRequest request) {
        Categoria novaCategoria = new Categoria();
        novaCategoria.setNome(request.nome());

        novaCategoria.setStatus(StatusCategoria.ATIVA);

        return categoriaRepository.save(novaCategoria);
    }
}
