package com.s1.Logitrack.controller;

import com.s1.Logitrack.dto.request.ProductoRequestDTO;
import com.s1.Logitrack.dto.response.ProductoResponseDTO;
import com.s1.Logitrack.service.impl.ProductoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Productos", description = "Gestión del catálogo de productos en LogiTrack")
@RestController
@RequestMapping("/api/producto")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoServiceImpl productoService;

    @Operation(summary = "Crear un producto", description = "Registra un nuevo producto en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos no válidos o body mal estructurado")
    })
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> guardar(@Valid @RequestBody ProductoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.guardarProducto(dto));
    }

    @Operation(summary = "Actualizar un producto", description = "Actualiza los datos de un producto existente por su ID.")
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizar(
            @Valid @RequestBody ProductoRequestDTO dto,
            @Parameter(description = "ID del producto a actualizar", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(productoService.actualizarProducto(dto, id));
    }

    @Operation(summary = "Listar todos los productos", description = "Retorna la lista completa de productos.")
    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(productoService.listarProductos());
    }

    @Operation(summary = "Buscar producto por ID", description = "Retorna un producto según su ID.")
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> buscarPorId(
            @Parameter(description = "ID del producto", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(productoService.buscarPorId(id));
    }

    @Operation(summary = "Buscar productos por categoría", description = "Filtra productos por nombre de categoría (no sensible a mayúsculas).")
    @GetMapping("/categoria")
    public ResponseEntity<List<ProductoResponseDTO>> buscarPorCategoria(
            @Parameter(description = "Nombre de la categoría", example = "Electrónica")
            @RequestParam String categoria) {
        return ResponseEntity.ok(productoService.buscarPorCategoria(categoria));
    }

    @Operation(summary = "Eliminar un producto", description = "Elimina un producto por su ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del producto a eliminar", example = "1")
            @PathVariable Long id) {
        productoService.eliminarProducto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
