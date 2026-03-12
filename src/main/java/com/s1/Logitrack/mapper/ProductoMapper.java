package com.s1.Logitrack.mapper;

import com.s1.Logitrack.dto.request.ProductoRequestDTO;
import com.s1.Logitrack.dto.response.ProductoResponseDTO;
import com.s1.Logitrack.model.Productos;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public ProductoResponseDTO entidadADTO(Productos producto) {
        if (producto == null) return null;
        return new ProductoResponseDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getCategoria(),
                producto.getPrecio()
        );
    }

    public Productos DTOAEntidad(ProductoRequestDTO dto) {
        if (dto == null) return null;
        Productos p = new Productos();
        p.setNombre(dto.nombre());
        p.setCategoria(dto.categoria());
        p.setPrecio(dto.precio());
        return p;
    }

    public void actualizarEntidadDesdeDTO(Productos producto, ProductoRequestDTO dto) {
        if (producto == null || dto == null) return;
        producto.setNombre(dto.nombre());
        producto.setCategoria(dto.categoria());
        producto.setPrecio(dto.precio());
    }
}
