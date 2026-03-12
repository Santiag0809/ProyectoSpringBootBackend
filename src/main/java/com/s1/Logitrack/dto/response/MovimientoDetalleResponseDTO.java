package com.s1.Logitrack.dto.response;

public record MovimientoDetalleResponseDTO(
        Long id,
        MovimientoResponseDTO movimiento,
        ProductoResponseDTO producto,
        Integer cantidad
) {
}