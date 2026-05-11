package com.senai.gestaosalas.dto;

import jakarta.validation.constraints.NotBlank;

public record Usuariodto(@NotBlank String nome, @NotBlank String email, @NotBlank String telefone, @NotBlank String cpf) {
}
