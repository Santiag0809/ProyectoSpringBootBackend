package com.s1.Logitrack.dto.response;

import com.s1.Logitrack.model.TipoOperacion;

import java.time.LocalDateTime;

public record AuditoriaResponseDTO(
        Long id,
        String entidad,
        TipoOperacion operacion,
        LocalDateTime fecha,
        PersonaResponseDTO usuario,
        String valorAnterior,
        String valorNuevo
) {
}
