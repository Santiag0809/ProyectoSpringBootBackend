package com.s1.Logitrack.controller;


import com.s1.Logitrack.dto.request.PersonaRequestDTO;
import com.s1.Logitrack.dto.response.PersonaResponseDTO;
import com.s1.Logitrack.service.impl.PersonaServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persona")
public class PersonaController {

    private final PersonaServiceImpl personaService;

    public PersonaController(PersonaServiceImpl personaService) {
        this.personaService = personaService;
    }

    // Crear persona
    @PostMapping
    public ResponseEntity<PersonaResponseDTO> guardar(
            @Valid @RequestBody PersonaRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaService.guardarPersona(dto));
    }

    // Actualizar persona
    @PutMapping("/{id}")
    public ResponseEntity<PersonaResponseDTO> actualizar(
            @Valid @RequestBody PersonaRequestDTO dto,
            @PathVariable Long id) {

        return ResponseEntity
                .ok(personaService.actualizarPersona(dto, id));
    }

    // Listar todas las personas
    @GetMapping("/public")
    public ResponseEntity<List<PersonaResponseDTO>> listarTodos() {

        return ResponseEntity
                .ok(personaService.listarPersonas());
    }

    // Filtrar personas por edad mayor a
    @Operation(
            summary = "Lista las edades mayores que",
            description = "Se espera poder filtrar quienes cumplen con la mayoria de cierta edad"
    )
    @GetMapping("/edad")
    public ResponseEntity<List<PersonaResponseDTO>> listarPorEdad(

            @Parameter(
                    description = "Edad mínima para filtrar personas",
                    example = "26"
            )
            @RequestParam Integer edad) {

        return ResponseEntity
                .ok(personaService.buscarMayorQueEdad(edad));
    }

    // Buscar persona por ID
    @GetMapping("/{id}")
    public ResponseEntity<PersonaResponseDTO> buscarId(
            @PathVariable Long id) {

        return ResponseEntity
                .ok(personaService.buscarPorId(id));
    }

    // Eliminar persona
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        personaService.eliminarPersona(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}