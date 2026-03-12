package com.s1.Logitrack.service.impl;

import com.s1.Logitrack.dto.request.BodegaRequestDTO;
import com.s1.Logitrack.dto.response.BodegaResponseDTO;
import com.s1.Logitrack.dto.response.PersonaResponseDTO;
import com.s1.Logitrack.mapper.BodegaMapper;
import com.s1.Logitrack.mapper.PersonaMapper;
import com.s1.Logitrack.model.Bodegas;
import com.s1.Logitrack.model.Persona;
import com.s1.Logitrack.repository.BodegaRepository;
import com.s1.Logitrack.repository.PersonaRepository;
import com.s1.Logitrack.service.BodegaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BodegaServiceImpl implements BodegaService {

    private final BodegaRepository bodegaRepository;
    private final BodegaMapper bodegaMapper;
    private final PersonaRepository personaRepository;
    private final PersonaMapper personaMapper;

    @Override
    public BodegaResponseDTO guardarBodega(BodegaRequestDTO dto) {
        Persona encargado = personaRepository.findById(dto.encargadoId())
                .orElseThrow(() -> new EntityNotFoundException("No existe el encargado con id: " + dto.encargadoId()));
        Bodegas bodega = bodegaMapper.DTOAEntidad(dto, encargado);
        Bodegas guardada = bodegaRepository.save(bodega);
        PersonaResponseDTO encargadoDTO = personaMapper.entidadADTO(encargado);
        return bodegaMapper.entidadADTO(guardada, encargadoDTO);
    }

    @Override
    public BodegaResponseDTO actualizarBodega(BodegaRequestDTO dto, Long id) {
        Bodegas bodega = bodegaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe la bodega con id: " + id));
        Persona encargado = personaRepository.findById(dto.encargadoId())
                .orElseThrow(() -> new EntityNotFoundException("No existe el encargado con id: " + dto.encargadoId()));
        bodegaMapper.actualizarEntidadDesdeDTO(bodega, dto, encargado);
        Bodegas actualizada = bodegaRepository.save(bodega);
        PersonaResponseDTO encargadoDTO = personaMapper.entidadADTO(encargado);
        return bodegaMapper.entidadADTO(actualizada, encargadoDTO);
    }

    @Override
    public void eliminarBodega(Long id) {
        Bodegas bodega = bodegaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe la bodega con id: " + id));
        bodegaRepository.delete(bodega);
    }

    @Override
    public List<BodegaResponseDTO> listarBodegas() {
        return bodegaRepository.findAll().stream()
                .map(b -> bodegaMapper.entidadADTO(b, personaMapper.entidadADTO(b.getEncargado())))
                .toList();
    }

    @Override
    public BodegaResponseDTO buscarPorId(Long id) {
        Bodegas bodega = bodegaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe la bodega con id: " + id));
        PersonaResponseDTO encargadoDTO = personaMapper.entidadADTO(bodega.getEncargado());
        return bodegaMapper.entidadADTO(bodega, encargadoDTO);
    }

    @Override
    public List<BodegaResponseDTO> buscarPorNombre(String nombre) {
        return bodegaRepository.findByNombreIgnoreCase(nombre).stream()
                .map(b -> bodegaMapper.entidadADTO(b, personaMapper.entidadADTO(b.getEncargado())))
                .toList();
    }

    @Override
    public List<BodegaResponseDTO> buscarPorEncargado(Long encargadoId) {
        return bodegaRepository.findByEncargadoId(encargadoId).stream()
                .map(b -> bodegaMapper.entidadADTO(b, personaMapper.entidadADTO(b.getEncargado())))
                .toList();
    }
}