package com.s1.Logitrack.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MovimientoDetalleRequestDTO(

        @Schema(description = "ID del movimiento al que pertenece el detalle", example = "1")
        @NotNull(message = "El ID del movimiento no puede ser nulo.")
        Long movimientoId,

        @Schema(description = "ID del producto", example = "3")
        @NotNull(message = "El ID del producto no puede ser nulo.")
        Long productoId,

        @Schema(description = "Cantidad de unidades del producto", example = "10")
        @NotNull(message = "La cantidad no puede ser nula.")
        @Positive(message = "La cantidad debe ser un valor positivo.")
        Integer cantidad
) {
}