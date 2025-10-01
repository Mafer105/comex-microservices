package br.com.alura.comex.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioPostRequest(@NotBlank @Email String email, 
    @NotBlank String senha) {
    
}
