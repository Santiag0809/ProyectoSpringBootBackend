package com.s1.Logitrack.service;

import com.s1.Logitrack.dto.request.MovimientoDetalleRequestDTO;
import com.s1.Logitrack.dto.response.MovimientoDetalleResponseDTO;

import java.util.List;

public interface MovimientoDetalleService {
    MovimientoDetalleResponseDTO guardarDetalle(MovimientoDetalleRequestDTO dto);
    MovimientoDetalleResponseDTO actualizarDetalle(MovimientoDetalleRequestDTO dto, Long id);
    void eliminarDetalle(Long id);
    List<MovimientoDetalleResponseDTO> listarDetalles();
    MovimientoDetalleResponseDTO buscarPorId(Long id);
    List<MovimientoDetalleResponseDTO> buscarPorMovimiento(Long movimientoId);
    List<MovimientoDetalleResponseDTO> buscarPorProducto(Long productoId);
}
