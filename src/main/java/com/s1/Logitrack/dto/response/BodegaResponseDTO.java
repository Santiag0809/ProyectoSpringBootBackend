package com.s1.Logitrack.dto.response;

public record BodegaResponseDTO(
        Long id,
        String nombre,
        String ubicacion,
        Integer capacidad,
        PersonaResponseDTO encargado
) {
}
