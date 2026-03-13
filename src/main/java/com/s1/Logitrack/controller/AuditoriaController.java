package com.s1.Logitrack.controller;

import com.s1.Logitrack.dto.request.AuditoriaRequestDTO;
import com.s1.Logitrack.dto.response.AuditoriaResponseDTO;
import com.s1.Logitrack.model.TipoOperacion;
import com.s1.Logitrack.service.impl.AuditoriaServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Auditorias", description = "Trazabilidad de operaciones en LogiTrack")
@RestController
@RequestMapping("/api/auditoria")
@RequiredArgsConstructor
public class AuditoriaController {

    private final AuditoriaServiceImpl auditoriaService;

    @Operation(summary = "Registrar una auditoría")
    @PostMapping
    public ResponseEntity<AuditoriaResponseDTO> guardar(@Valid @RequestBody AuditoriaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(auditoriaService.guardarAuditoria(dto));
    }

    @Operation(summary = "Listar todas las auditorías")
    @GetMapping
    public ResponseEntity<List<AuditoriaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(auditoriaService.listarAuditorias());
    }

    @Operation(summary = "Buscar auditoría por ID")
    @GetMapping("/{id}")
    public ResponseEntity<AuditoriaResponseDTO> buscarPorId(
            @Parameter(description = "ID de la auditoría", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(auditoriaService.buscarPorId(id));
    }

    @Operation(summary = "Buscar auditorías por entidad")
    @GetMapping("/entidad")
    public ResponseEntity<List<AuditoriaResponseDTO>> buscarPorEntidad(
            @Parameter(description = "Nombre de la entidad", example = "productos")
            @RequestParam String entidad) {
        return ResponseEntity.ok(auditoriaService.buscarPorEntidad(entidad));
    }

    @Operation(summary = "Buscar auditorías por operación", description = "Operaciones: INSERT, UPDATE, DELETE")
    @GetMapping("/operacion")
    public ResponseEntity<List<AuditoriaResponseDTO>> buscarPorOperacion(
            @Parameter(description = "Tipo de operación", example = "INSERT")
            @RequestParam TipoOperacion operacion) {
        return ResponseEntity.ok(auditoriaService.buscarPorOperacion(operacion));
    }

    @Operation(summary = "Buscar auditorías por usuario")
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<AuditoriaResponseDTO>> buscarPorUsuario(
            @Parameter(description = "ID del usuario", example = "1")
            @PathVariable Long usuarioId) {
        return ResponseEntity.ok(auditoriaService.buscarPorUsuario(usuarioId));
    }

    @Operation(summary = "Eliminar una auditoría")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID de la auditoría a eliminar", example = "1")
            @PathVariable Long id) {
        auditoriaService.eliminarAuditoria(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
