package br.com.alura.comex.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"logradouro", "bairro", "cidade", "estado", "cep"}) 
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    private String telefone;

    private String logradouro;
    private String bairro;
    private String cidade;
    
    private String estado;
    
    @Column(length = 8)
    private String cep;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false, unique = true) private Usuario usuario;
}