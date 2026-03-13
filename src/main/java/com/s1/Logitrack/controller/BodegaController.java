package com.s1.Logitrack.controller;

import com.s1.Logitrack.dto.request.BodegaRequestDTO;
import com.s1.Logitrack.dto.response.BodegaResponseDTO;
import com.s1.Logitrack.service.impl.BodegaServiceImpl;
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

@Tag(name = "Bodegas", description = "Gestión de bodegas en LogiTrack")
@RestController
@RequestMapping("/api/bodega")
@RequiredArgsConstructor
public class BodegaController {

    private final BodegaServiceImpl bodegaService;

    @Operation(summary = "Crear una bodega")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Bodega creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos no válidos")
    })
    @PostMapping
    public ResponseEntity<BodegaResponseDTO> guardar(@Valid @RequestBody BodegaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bodegaService.guardarBodega(dto));
    }

    @Operation(summary = "Actualizar una bodega")
    @PutMapping("/{id}")
    public ResponseEntity<BodegaResponseDTO> actualizar(
            @Valid @RequestBody BodegaRequestDTO dto,
            @Parameter(description = "ID de la bodega a actualizar", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(bodegaService.actualizarBodega(dto, id));
    }

    @Operation(summary = "Listar todas las bodegas")
    @GetMapping
    public ResponseEntity<List<BodegaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(bodegaService.listarBodegas());
    }

    @Operation(summary = "Buscar bodega por ID")
    @GetMapping("/{id}")
    public ResponseEntity<BodegaResponseDTO> buscarPorId(
            @Parameter(description = "ID de la bodega", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(bodegaService.buscarPorId(id));
    }

    @Operation(summary = "Buscar bodegas por nombre")
    @GetMapping("/nombre")
    public ResponseEntity<List<BodegaResponseDTO>> buscarPorNombre(
            @Parameter(description = "Nombre de la bodega", example = "Bodega Central")
            @RequestParam String nombre) {
        return ResponseEntity.ok(bodegaService.buscarPorNombre(nombre));
    }

    @Operation(summary = "Buscar bodegas por encargado")
    @GetMapping("/encargado/{encargadoId}")
    public ResponseEntity<List<BodegaResponseDTO>> buscarPorEncargado(
            @Parameter(description = "ID del encargado", example = "1")
            @PathVariable Long encargadoId) {
        return ResponseEntity.ok(bodegaService.buscarPorEncargado(encargadoId));
    }

    @Operation(summary = "Eliminar una bodega")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID de la bodega a eliminar", example = "1")
            @PathVariable Long id) {
        bodegaService.eliminarBodega(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
