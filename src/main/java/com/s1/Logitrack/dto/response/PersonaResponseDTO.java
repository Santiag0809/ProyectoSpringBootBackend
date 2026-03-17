package com.s1.Logitrack.dto.response;

public record PersonaResponseDTO(
        Long id,
        String nombre,
        String apellido,
        Integer edad,
        String documento,
        String email,
        String rol
) {}
