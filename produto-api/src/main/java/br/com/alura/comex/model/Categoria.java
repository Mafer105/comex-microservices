package br.com.alura.comex.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorias")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "produtos") 
@EqualsAndHashCode(of = "id")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Enumerated(EnumType.STRING) 
    private StatusCategoria status;

    @ManyToMany(mappedBy = "categorias")
    private List<Produto> produtos = new ArrayList<>();
}