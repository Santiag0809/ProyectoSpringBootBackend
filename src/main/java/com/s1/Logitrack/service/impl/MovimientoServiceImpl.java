package com.s1.Logitrack.service.impl;

import com.s1.Logitrack.dto.request.MovimientoRequestDTO;
import com.s1.Logitrack.dto.response.BodegaResponseDTO;
import com.s1.Logitrack.dto.response.MovimientoResponseDTO;
import com.s1.Logitrack.dto.response.PersonaResponseDTO;
import com.s1.Logitrack.mapper.BodegaMapper;
import com.s1.Logitrack.mapper.MovimientoMapper;
import com.s1.Logitrack.mapper.PersonaMapper;
import com.s1.Logitrack.model.Bodegas;
import com.s1.Logitrack.model.Movimientos;
import com.s1.Logitrack.model.Persona;
import com.s1.Logitrack.model.TipoMovimiento;
import com.s1.Logitrack.repository.BodegaRepository;
import com.s1.Logitrack.repository.MovimientoRepository;
import com.s1.Logitrack.repository.PersonaRepository;
import com.s1.Logitrack.service.MovimientoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final MovimientoMapper movimientoMapper;
    private final PersonaRepository personaRepository;
    private final PersonaMapper personaMapper;
    private final BodegaRepository bodegaRepository;
    private final BodegaMapper bodegaMapper;

    private BodegaResponseDTO resolverBodegaDTO(Long id) {
        if (id == null) return null;
        Bodegas b = bodegaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe la bodega con id: " + id));
        return bodegaMapper.entidadADTO(b, personaMapper.entidadADTO(b.getEncargado()));
    }

    private Bodegas resolverBodega(Long id) {
        if (id == null) return null;
        return bodegaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe la bodega con id: " + id));
    }

    private MovimientoResponseDTO toDTO(Movimientos m) {
        PersonaResponseDTO usuarioDTO = personaMapper.entidadADTO(m.getUsuario());
        BodegaResponseDTO origenDTO = m.getBodegaOrigen() != null
                ? bodegaMapper.entidadADTO(m.getBodegaOrigen(), personaMapper.entidadADTO(m.getBodegaOrigen().getEncargado()))
                : null;
        BodegaResponseDTO destinoDTO = m.getBodegaDestino() != null
                ? bodegaMapper.entidadADTO(m.getBodegaDestino(), personaMapper.entidadADTO(m.getBodegaDestino().getEncargado()))
                : null;
        return movimientoMapper.entidadADTO(m, usuarioDTO, origenDTO, destinoDTO);
    }

    @Override
    public MovimientoResponseDTO guardarMovimiento(MovimientoRequestDTO dto) {
        Persona usuario = personaRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new EntityNotFoundException("No existe el usuario con id: " + dto.usuarioId()));
        Movimientos m = movimientoMapper.DTOAEntidad(dto, usuario,
                resolverBodega(dto.bodegaOrigenId()),
                resolverBodega(dto.bodegaDestinoId()));
        return toDTO(movimientoRepository.save(m));
    }

    @Override
    public MovimientoResponseDTO actualizarMovimiento(MovimientoRequestDTO dto, Long id) {
        Movimientos m = movimientoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe el movimiento con id: " + id));
        Persona usuario = personaRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new EntityNotFoundException("No existe el usuario con id: " + dto.usuarioId()));
        movimientoMapper.actualizarEntidadDesdeDTO(m, dto, usuario,
                resolverBodega(dto.bodegaOrigenId()),
                resolverBodega(dto.bodegaDestinoId()));
        return toDTO(movimientoRepository.save(m));
    }

    @Override
    public void eliminarMovimiento(Long id) {
        Movimientos m = movimientoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe el movimiento con id: " + id));
        movimientoRepository.delete(m);
    }

    @Override
    public List<MovimientoResponseDTO> listarMovimientos() {
        return movimientoRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public MovimientoResponseDTO buscarPorId(Long id) {
        Movimientos m = movimientoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe el movimiento con id: " + id));
        return toDTO(m);
    }

    @Override
    public List<MovimientoResponseDTO> buscarPorTipo(TipoMovimiento tipo) {
        return movimientoRepository.findByTipoMovimiento(tipo).stream().map(this::toDTO).toList();
    }

    @Override
    public List<MovimientoResponseDTO> buscarPorUsuario(Long usuarioId) {
        return movimientoRepository.findByUsuarioId(usuarioId).stream().map(this::toDTO).toList();
    }
}
