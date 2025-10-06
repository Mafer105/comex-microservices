package br.com.alura.comex.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios_cache")
@Getter 
@Setter 
@NoArgsConstructor 
public class UsuarioCache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    private String nome;

    @Column(length = 512) 
    private String token;

    private boolean ativo;

    private LocalDateTime ultimaAtualizacao;
}
