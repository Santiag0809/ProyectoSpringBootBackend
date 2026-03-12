package com.s1.Logitrack.service.impl;

import com.s1.Logitrack.dto.request.ProductoRequestDTO;
import com.s1.Logitrack.dto.response.ProductoResponseDTO;
import com.s1.Logitrack.mapper.ProductoMapper;
import com.s1.Logitrack.model.Productos;
import com.s1.Logitrack.repository.ProductoRepository;
import com.s1.Logitrack.service.ProductoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    @Override
    public ProductoResponseDTO guardarProducto(ProductoRequestDTO dto) {
        Productos p = productoMapper.DTOAEntidad(dto);
        Productos guardado = productoRepository.save(p);
        return productoMapper.entidadADTO(guardado);
    }

    @Override
    public ProductoResponseDTO actualizarProducto(ProductoRequestDTO dto, Long id) {
        Productos p = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe el producto con id: " + id));
        productoMapper.actualizarEntidadDesdeDTO(p, dto);
        Productos actualizado = productoRepository.save(p);
        return productoMapper.entidadADTO(actualizado);
    }

    @Override
    public void eliminarProducto(Long id) {
        Productos p = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe el producto con id: " + id));
        productoRepository.delete(p);
    }

    @Override
    public List<ProductoResponseDTO> listarProductos() {
        return productoRepository.findAll()
                .stream()
                .map(productoMapper::entidadADTO)
                .toList();
    }

    @Override
    public ProductoResponseDTO buscarPorId(Long id) {
        Productos p = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe el producto con id: " + id));
        return productoMapper.entidadADTO(p);
    }

    @Override
    public List<ProductoResponseDTO> buscarPorCategoria(String categoria) {
        return productoRepository.findByCategoriaIgnoreCase(categoria)
                .stream()
                .map(productoMapper::entidadADTO)
                .toList();
    }

    @Override
    public boolean existePorNombre(String nombre) {
        return productoRepository.existsByNombre(nombre);
    }
}
