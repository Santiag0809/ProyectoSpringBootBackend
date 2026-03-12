package com.s1.Logitrack.service.impl;

import com.s1.Logitrack.dto.request.MovimientoDetalleRequestDTO;
import com.s1.Logitrack.dto.response.MovimientoDetalleResponseDTO;
import com.s1.Logitrack.dto.response.MovimientoResponseDTO;
import com.s1.Logitrack.dto.response.ProductoResponseDTO;
import com.s1.Logitrack.mapper.BodegaMapper;
import com.s1.Logitrack.mapper.MovimientoDetalleMapper;
import com.s1.Logitrack.mapper.MovimientoMapper;
import com.s1.Logitrack.mapper.PersonaMapper;
import com.s1.Logitrack.mapper.ProductoMapper;
import com.s1.Logitrack.model.MovimientoDetalles;
import com.s1.Logitrack.model.Movimientos;
import com.s1.Logitrack.model.Productos;
import com.s1.Logitrack.repository.MovimientoDetalleRepository;
import com.s1.Logitrack.repository.MovimientoRepository;
import com.s1.Logitrack.repository.ProductoRepository;
import com.s1.Logitrack.service.MovimientoDetalleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovimientoDetalleServiceImpl implements MovimientoDetalleService {

    private final MovimientoDetalleRepository detalleRepository;
    private final MovimientoDetalleMapper detalleMapper;
    private final MovimientoRepository movimientoRepository;
    private final MovimientoMapper movimientoMapper;
    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;
    private final PersonaMapper personaMapper;
    private final BodegaMapper bodegaMapper;

    private MovimientoResponseDTO toMovimientoDTO(Movimientos m) {
        return movimientoMapper.entidadADTO(
                m,
                personaMapper.entidadADTO(m.getUsuario()),
                m.getBodegaOrigen() != null ? bodegaMapper.entidadADTO(m.getBodegaOrigen(), personaMapper.entidadADTO(m.getBodegaOrigen().getEncargado())) : null,
                m.getBodegaDestino() != null ? bodegaMapper.entidadADTO(m.getBodegaDestino(), personaMapper.entidadADTO(m.getBodegaDestino().getEncargado())) : null
        );
    }

    private MovimientoDetalleResponseDTO toDTO(MovimientoDetalles d) {
        MovimientoResponseDTO movDTO = toMovimientoDTO(d.getMovimiento());
        ProductoResponseDTO prodDTO = productoMapper.entidadADTO(d.getProducto());
        return detalleMapper.entidadADTO(d, movDTO, prodDTO);
    }

    @Override
    public MovimientoDetalleResponseDTO guardarDetalle(MovimientoDetalleRequestDTO dto) {
        Movimientos movimiento = movimientoRepository.findById(dto.movimientoId())
                .orElseThrow(() -> new EntityNotFoundException("No existe el movimiento con id: " + dto.movimientoId()));
        Productos producto = productoRepository.findById(dto.productoId())
                .orElseThrow(() -> new EntityNotFoundException("No existe el producto con id: " + dto.productoId()));
        MovimientoDetalles detalle = detalleMapper.DTOAEntidad(dto, movimiento, producto);
        return toDTO(detalleRepository.save(detalle));
    }

    @Override
    public MovimientoDetalleResponseDTO actualizarDetalle(MovimientoDetalleRequestDTO dto, Long id) {
        MovimientoDetalles detalle = detalleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe el detalle con id: " + id));
        Movimientos movimiento = movimientoRepository.findById(dto.movimientoId())
                .orElseThrow(() -> new EntityNotFoundException("No existe el movimiento con id: " + dto.movimientoId()));
        Productos producto = productoRepository.findById(dto.productoId())
                .orElseThrow(() -> new EntityNotFoundException("No existe el producto con id: " + dto.productoId()));
        detalleMapper.actualizarEntidadDesdeDTO(detalle, dto, movimiento, producto);
        return toDTO(detalleRepository.save(detalle));
    }

    @Override
    public void eliminarDetalle(Long id) {
        MovimientoDetalles detalle = detalleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe el detalle con id: " + id));
        detalleRepository.delete(detalle);
    }

    @Override
    public List<MovimientoDetalleResponseDTO> listarDetalles() {
        return detalleRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public MovimientoDetalleResponseDTO buscarPorId(Long id) {
        MovimientoDetalles detalle = detalleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe el detalle con id: " + id));
        return toDTO(detalle);
    }

    @Override
    public List<MovimientoDetalleResponseDTO> buscarPorMovimiento(Long movimientoId) {
        return detalleRepository.findByMovimientoId(movimientoId).stream().map(this::toDTO).toList();
    }

    @Override
    public List<MovimientoDetalleResponseDTO> buscarPorProducto(Long productoId) {
        return detalleRepository.findByProductoId(productoId).stream().map(this::toDTO).toList();
    }
}