package com.s1.Logitrack.service;

import com.s1.Logitrack.dto.request.AuditoriaRequestDTO;
import com.s1.Logitrack.dto.response.AuditoriaResponseDTO;
import com.s1.Logitrack.model.TipoOperacion;

import java.util.List;

public interface AuditoriaService {
    AuditoriaResponseDTO guardarAuditoria(AuditoriaRequestDTO dto);
    List<AuditoriaResponseDTO> listarAuditorias();
    AuditoriaResponseDTO buscarPorId(Long id);
    List<AuditoriaResponseDTO> buscarPorEntidad(String entidad);
    List<AuditoriaResponseDTO> buscarPorOperacion(TipoOperacion operacion);
    List<AuditoriaResponseDTO> buscarPorUsuario(Long usuarioId);
    void eliminarAuditoria(Long id);
}