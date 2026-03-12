package com.s1.Logitrack.mapper;

import com.s1.Logitrack.dto.request.MovimientoDetalleRequestDTO;
import com.s1.Logitrack.dto.response.MovimientoDetalleResponseDTO;
import com.s1.Logitrack.dto.response.MovimientoResponseDTO;
import com.s1.Logitrack.dto.response.ProductoResponseDTO;
import com.s1.Logitrack.model.MovimientoDetalles;
import com.s1.Logitrack.model.Movimientos;
import com.s1.Logitrack.model.Productos;
import org.springframework.stereotype.Component;

@Component
public class MovimientoDetalleMapper {

    public MovimientoDetalleResponseDTO entidadADTO(MovimientoDetalles detalle,
                                                    MovimientoResponseDTO movimientoDTO,
                                                    ProductoResponseDTO productoDTO) {
        if (detalle == null || movimientoDTO == null || productoDTO == null) return null;
        return new MovimientoDetalleResponseDTO(
                detalle.getId(),
                movimientoDTO,
                productoDTO,
                detalle.getCantidad()
        );
    }

    public MovimientoDetalles DTOAEntidad(MovimientoDetalleRequestDTO dto,
                                          Movimientos movimiento,
                                          Productos producto) {
        if (dto == null || movimiento == null || producto == null) return null;
        MovimientoDetalles d = new MovimientoDetalles();
        d.setMovimiento(movimiento);
        d.setProducto(producto);
        d.setCantidad(dto.cantidad());
        return d;
    }

    public void actualizarEntidadDesdeDTO(MovimientoDetalles detalle,
                                          MovimientoDetalleRequestDTO dto,
                                          Movimientos movimiento,
                                          Productos producto) {
        if (detalle == null || dto == null || movimiento == null || producto == null) return;
        detalle.setMovimiento(movimiento);
        detalle.setProducto(producto);
        detalle.setCantidad(dto.cantidad());
    }
}