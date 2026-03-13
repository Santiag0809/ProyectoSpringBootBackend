package com.s1.Logitrack.controller;

import com.s1.Logitrack.dto.request.MovimientoRequestDTO;
import com.s1.Logitrack.dto.response.MovimientoResponseDTO;
import com.s1.Logitrack.model.TipoMovimiento;
import com.s1.Logitrack.service.impl.MovimientoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Movimientos", description = "Gestión de movimientos de inventario en LogiTrack")
@RestController
@RequestMapping("/api/movimiento")
@RequiredArgsConstructor
public class MovimientoController {

    private final MovimientoServiceImpl movimientoService;

    @Operation(summary = "Registrar un movimiento")
    @PostMapping
    public ResponseEntity<MovimientoResponseDTO> guardar(@Valid @RequestBody MovimientoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movimientoService.guardarMovimiento(dto));
    }

    @Operation(summary = "Actualizar un movimiento")
    @PutMapping("/{id}")
    public ResponseEntity<MovimientoResponseDTO> actualizar(
            @Valid @RequestBody MovimientoRequestDTO dto,
            @Parameter(description = "ID del movimiento a actualizar", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(movimientoService.actualizarMovimiento(dto, id));
    }

    @Operation(summary = "Listar todos los movimientos")
    @GetMapping
    public ResponseEntity<List<MovimientoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(movimientoService.listarMovimientos());
    }

    @Operation(summary = "Buscar movimiento por ID")
    @GetMapping("/{id}")
    public ResponseEntity<MovimientoResponseDTO> buscarPorId(
            @Parameter(description = "ID del movimiento", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(movimientoService.buscarPorId(id));
    }

    @Operation(summary = "Buscar movimientos por tipo", description = "Tipos: ENTRADA, SALIDA, TRANSFERENCIA")
    @GetMapping("/tipo")
    public ResponseEntity<List<MovimientoResponseDTO>> buscarPorTipo(
            @Parameter(description = "Tipo de movimiento", example = "ENTRADA")
            @RequestParam TipoMovimiento tipo) {
        return ResponseEntity.ok(movimientoService.buscarPorTipo(tipo));
    }

    @Operation(summary = "Buscar movimientos por usuario")
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<MovimientoResponseDTO>> buscarPorUsuario(
            @Parameter(description = "ID del usuario", example = "1")
            @PathVariable Long usuarioId) {
        return ResponseEntity.ok(movimientoService.buscarPorUsuario(usuarioId));
    }

    @Operation(summary = "Eliminar un movimiento")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del movimiento a eliminar", example = "1")
            @PathVariable Long id) {
        movimientoService.eliminarMovimiento(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
