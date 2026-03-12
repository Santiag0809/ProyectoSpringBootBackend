package com.s1.Logitrack.service;

import com.s1.Logitrack.dto.request.BodegaRequestDTO;
import com.s1.Logitrack.dto.response.BodegaResponseDTO;

import java.util.List;

public interface BodegaService {
    BodegaResponseDTO guardarBodega(BodegaRequestDTO dto);
    BodegaResponseDTO actualizarBodega(BodegaRequestDTO dto, Long id);
    void eliminarBodega(Long id);
    List<BodegaResponseDTO> listarBodegas();
    BodegaResponseDTO buscarPorId(Long id);
    List<BodegaResponseDTO> buscarPorNombre(String nombre);
    List<BodegaResponseDTO> buscarPorEncargado(Long encargadoId);
}