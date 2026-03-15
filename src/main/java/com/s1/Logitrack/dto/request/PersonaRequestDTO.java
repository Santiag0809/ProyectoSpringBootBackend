package com.s1.Logitrack.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public record PersonaRequestDTO(

        @Schema(description = "Nombre de la persona",
                example = "Juan")
        @NotBlank(message = "El nombre no puede estar vacío")
        String nombre,

        @Schema(description = "Apellido de la persona",
                example = "Perez")
        @NotBlank(message = "El apellido no puede estar vacío")
        @Size(min = 2, max = 30, message = "El apellido debe tener entre 2 y 30 caracteres")
        String apellido,

        @Schema(description = "Edad de la persona",
                example = "25")
        @NotNull(message = "La edad no puede ser nula")
        @Positive(message = "La edad debe ser positiva")
        Integer edad,

        @Schema(description = "Documento de la persona",
                example = "109822111")
        @NotNull(message = "El documento no puede ser nulo o vacio")
        String documento,

        @Schema(description = "Correo electrónico",
                example = "juan@gmail.com")
        @NotBlank(message = "El email es obligatorio")
        @Email(message = "Debe ser un email válido")
        String email,

        @Schema(description = "Contraseña del usuario",
                example = "123456")
        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 6, message = "La contraseña debe tener mínimo 6 caracteres")
        String password
) {}