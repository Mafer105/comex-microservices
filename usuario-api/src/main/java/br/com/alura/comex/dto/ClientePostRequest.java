package br.com.alura.comex.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ClientePostRequest( @NotBlank String nome,
    @NotBlank @Email String email,
    @NotBlank String senha,
    @NotBlank @Pattern(regexp = "\\d{11}") String cpf,
    String telefone) {
} 