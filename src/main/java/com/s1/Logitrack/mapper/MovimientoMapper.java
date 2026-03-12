package com.s1.Logitrack.mapper;

import com.s1.Logitrack.dto.request.MovimientoRequestDTO;
import com.s1.Logitrack.dto.response.BodegaResponseDTO;
import com.s1.Logitrack.dto.response.MovimientoResponseDTO;
import com.s1.Logitrack.dto.response.PersonaResponseDTO;
import com.s1.Logitrack.model.Bodegas;
import com.s1.Logitrack.model.Movimientos;
import com.s1.Logitrack.model.Persona;
import org.springframework.stereotype.Component;

@Component
public class MovimientoMapper {

    public MovimientoResponseDTO entidadADTO(Movimientos movimiento,
                                             PersonaResponseDTO usuarioDTO,
                                             BodegaResponseDTO origenDTO,
                                             BodegaResponseDTO destinoDTO) {
        if (movimiento == null || usuarioDTO == null) return null;
        return new MovimientoResponseDTO(
                movimiento.getId(),
                movimiento.getFecha(),
                movimiento.getTipoMovimiento(),
                usuarioDTO,
                origenDTO,
                destinoDTO
        );
    }

    public Movimientos DTOAEntidad(MovimientoRequestDTO dto,
                                   Persona usuario,
                                   Bodegas bodegaOrigen,
                                   Bodegas bodegaDestino) {
        if (dto == null || usuario == null) return null;
        Movimientos m = new Movimientos();
        m.setFecha(dto.fecha());
        m.setTipoMovimiento(dto.tipoMovimiento());
        m.setUsuario(usuario);
        m.setBodegaOrigen(bodegaOrigen);
        m.setBodegaDestino(bodegaDestino);
        return m;
    }

    public void actualizarEntidadDesdeDTO(Movimientos movimiento,
                                          MovimientoRequestDTO dto,
                                          Persona usuario,
                                          Bodegas bodegaOrigen,
                                          Bodegas bodegaDestino) {
        if (movimiento == null || dto == null || usuario == null) return;
        movimiento.setFecha(dto.fecha());
        movimiento.setTipoMovimiento(dto.tipoMovimiento());
        movimiento.setUsuario(usuario);
        movimiento.setBodegaOrigen(bodegaOrigen);
        movimiento.setBodegaDestino(bodegaDestino);
    }
}