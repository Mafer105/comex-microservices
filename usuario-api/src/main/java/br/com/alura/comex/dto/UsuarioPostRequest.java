package br.com.alura.comex.dto;

import br.com.alura.comex.model.Perfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioPostRequest(@NotBlank
    String nome, @NotBlank @Email String email, 
    @NotBlank String senha, @NotNull Perfil perfil) {
    
}
