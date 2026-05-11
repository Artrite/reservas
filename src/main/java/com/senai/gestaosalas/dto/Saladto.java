package com.senai.gestaosalas.dto;

import jakarta.validation.constraints.NotBlank;

public record Saladto (@NotBlank String nome, @NotBlank String departamento, String descricao, @NotBlank int status) {
}
