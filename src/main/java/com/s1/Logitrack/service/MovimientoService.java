package com.s1.Logitrack.service;

import com.s1.Logitrack.dto.request.MovimientoRequestDTO;
import com.s1.Logitrack.dto.response.MovimientoResponseDTO;
import com.s1.Logitrack.model.TipoMovimiento;

import java.util.List;

public interface MovimientoService {
    MovimientoResponseDTO guardarMovimiento(MovimientoRequestDTO dto);
    MovimientoResponseDTO actualizarMovimiento(MovimientoRequestDTO dto, Long id);
    void eliminarMovimiento(Long id);
    List<MovimientoResponseDTO> listarMovimientos();
    MovimientoResponseDTO buscarPorId(Long id);
    List<MovimientoResponseDTO> buscarPorTipo(TipoMovimiento tipo);
    List<MovimientoResponseDTO> buscarPorUsuario(Long usuarioId);
}
