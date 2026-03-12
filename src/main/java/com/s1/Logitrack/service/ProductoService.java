package com.s1.Logitrack.service;

import com.s1.Logitrack.dto.request.ProductoRequestDTO;
import com.s1.Logitrack.dto.response.ProductoResponseDTO;

import java.util.List;

public interface ProductoService {

    ProductoResponseDTO guardarProducto(ProductoRequestDTO dto);

    ProductoResponseDTO actualizarProducto(ProductoRequestDTO dto, Long id);

    void eliminarProducto(Long id);

    List<ProductoResponseDTO> listarProductos();

    ProductoResponseDTO buscarPorId(Long id);

    List<ProductoResponseDTO> buscarPorCategoria(String categoria);

    boolean existePorNombre(String nombre);
}
