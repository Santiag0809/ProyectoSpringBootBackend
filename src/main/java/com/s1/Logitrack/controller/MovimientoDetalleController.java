package com.s1.Logitrack.controller;

import com.s1.Logitrack.dto.request.MovimientoDetalleRequestDTO;
import com.s1.Logitrack.dto.response.MovimientoDetalleResponseDTO;
import com.s1.Logitrack.service.impl.MovimientoDetalleServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Movimiento Detalles", description = "Gestión de detalles de movimientos en LogiTrack")
@RestController
@RequestMapping("/api/movimiento-detalle")
@RequiredArgsConstructor
public class MovimientoDetalleController {

    private final MovimientoDetalleServiceImpl detalleService;

    @Operation(summary = "Registrar un detalle de movimiento")
    @PostMapping
    public ResponseEntity<MovimientoDetalleResponseDTO> guardar(@Valid @RequestBody MovimientoDetalleRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(detalleService.guardarDetalle(dto));
    }

    @Operation(summary = "Actualizar un detalle de movimiento")
    @PutMapping("/{id}")
    public ResponseEntity<MovimientoDetalleResponseDTO> actualizar(
            @Valid @RequestBody MovimientoDetalleRequestDTO dto,
            @Parameter(description = "ID del detalle a actualizar", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(detalleService.actualizarDetalle(dto, id));
    }

    @Operation(summary = "Listar todos los detalles")
    @GetMapping
    public ResponseEntity<List<MovimientoDetalleResponseDTO>> listarTodos() {
        return ResponseEntity.ok(detalleService.listarDetalles());
    }

    @Operation(summary = "Buscar detalle por ID")
    @GetMapping("/{id}")
    public ResponseEntity<MovimientoDetalleResponseDTO> buscarPorId(
            @Parameter(description = "ID del detalle", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(detalleService.buscarPorId(id));
    }

    @Operation(summary = "Buscar detalles por movimiento")
    @GetMapping("/movimiento/{movimientoId}")
    public ResponseEntity<List<MovimientoDetalleResponseDTO>> buscarPorMovimiento(
            @Parameter(description = "ID del movimiento", example = "1")
            @PathVariable Long movimientoId) {
        return ResponseEntity.ok(detalleService.buscarPorMovimiento(movimientoId));
    }

    @Operation(summary = "Buscar detalles por producto")
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<MovimientoDetalleResponseDTO>> buscarPorProducto(
            @Parameter(description = "ID del producto", example = "1")
            @PathVariable Long productoId) {
        return ResponseEntity.ok(detalleService.buscarPorProducto(productoId));
    }

    @Operation(summary = "Eliminar un detalle de movimiento")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del detalle a eliminar", example = "1")
            @PathVariable Long id) {
        detalleService.eliminarDetalle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
