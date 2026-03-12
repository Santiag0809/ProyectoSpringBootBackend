package com.s1.Logitrack.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record BodegaRequestDTO(

        @Schema(description = "Nombre de la bodega", example = "Bodega Central")
        @NotBlank(message = "El nombre no puede estar vacío.")
        @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres.")
        String nombre,

        @Schema(description = "Ubicación de la bodega", example = "Zona Industrial Norte")
        @NotBlank(message = "La ubicación no puede estar vacía.")
        @Size(min = 2, max = 150, message = "La ubicación debe tener entre 2 y 150 caracteres.")
        String ubicacion,

        @Schema(description = "Capacidad máxima de la bodega", example = "500")
        @NotNull(message = "La capacidad no puede ser nula.")
        @Positive(message = "La capacidad debe ser un valor positivo.")
        Integer capacidad,

        @Schema(description = "ID del encargado de la bodega", example = "1")
        @NotNull(message = "El ID del encargado no puede ser nulo.")
        Long encargadoId
) {
}
