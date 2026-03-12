package com.s1.Logitrack.dto.response;

import com.s1.Logitrack.model.TipoMovimiento;
import java.time.LocalDateTime;

public record MovimientoResponseDTO(
        Long id,
        LocalDateTime fecha,
        TipoMovimiento tipoMovimiento,
        PersonaResponseDTO usuario,
        BodegaResponseDTO bodegaOrigen,
        BodegaResponseDTO bodegaDestino
) {
}

