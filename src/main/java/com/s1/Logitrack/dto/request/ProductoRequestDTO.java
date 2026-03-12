package com.s1.Logitrack.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductoRequestDTO(

        @Schema(description = "Nombre del producto", example = "Cemento gris")
        @NotBlank(message = "El nombre no puede estar vacío.")
        @Size(min = 2, max = 150, message = "El nombre debe tener entre 2 y 150 caracteres.")
        String nombre,

        @Schema(description = "Categoría del producto", example = "Materiales de construcción")
        @NotBlank(message = "La categoría no puede estar vacía.")
        @Size(min = 2, max = 100, message = "La categoría debe tener entre 2 y 100 caracteres.")
        String categoria,

        @Schema(description = "Precio del producto", example = "25000.00")
        @NotNull(message = "El precio no puede ser nulo.")
        @Positive(message = "El precio debe ser un valor positivo.")
        BigDecimal precio
) {}