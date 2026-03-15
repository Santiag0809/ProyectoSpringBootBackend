package com.s1.Logitrack.dto.request;

import com.s1.Logitrack.model.TipoOperacion;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AuditoriaRequestDTO(

        @Schema(description = "Nombre de la entidad afectada", example = "productos")
        @NotBlank(message = "La entidad no puede estar vacía.")
        String entidad,

        @Schema(description = "Tipo de operación: INSERT, UPDATE o DELETE", example = "INSERT")
        @NotNull(message = "La operación no puede ser nula.")
        TipoOperacion operacion,

        @Schema(description = "ID del usuario que realizó la operación", example = "1")
        @NotNull(message = "El ID del usuario no puede ser nulo.")
        Long usuarioId,

        @Schema(description = "Valor anterior en texto plano", example = "nombre: torre de computador")
        String valorAnterior,

        @Schema(description = "Valor nuevo en texto plano", example = "nombre: cemento gris")
        String valorNuevo
) {
}