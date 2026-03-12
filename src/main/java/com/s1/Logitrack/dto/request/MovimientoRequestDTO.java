package com.s1.Logitrack.dto.request;

import com.s1.Logitrack.model.TipoMovimiento;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record MovimientoRequestDTO(

        @Schema(description = "Fecha y hora del movimiento", example = "2026-03-12T10:30:00")
        @NotNull(message = "La fecha no puede ser nula.")
        LocalDateTime fecha,

        @Schema(description = "Tipo de movimiento: ENTRADA, SALIDA o TRANSFERENCIA", example = "ENTRADA")
        @NotNull(message = "El tipo de movimiento no puede ser nulo.")
        TipoMovimiento tipoMovimiento,

        @Schema(description = "ID del usuario que realiza el movimiento", example = "1")
        @NotNull(message = "El ID del usuario no puede ser nulo.")
        Long usuarioId,

        @Schema(description = "ID de la bodega origen (opcional para ENTRADA)", example = "1")
        Long bodegaOrigenId,

        @Schema(description = "ID de la bodega destino (opcional para SALIDA)", example = "2")
        Long bodegaDestinoId
) {
}