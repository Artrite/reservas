package com.senai.gestaosalas.dto;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record Reservadto ( @NotNull LocalDateTime data_reserva, @NotNull LocalDateTime data_cadastro, @NotNull int status,
                @NotNull Long id_sala, @NotNull Long id_usuario) {

}

