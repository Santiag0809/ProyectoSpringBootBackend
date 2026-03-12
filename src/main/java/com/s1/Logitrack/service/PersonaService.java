package com.s1.Logitrack.service;


import com.s1.Logitrack.dto.request.PersonaRequestDTO;
import com.s1.Logitrack.dto.response.PersonaResponseDTO;

import java.util.List;

public interface PersonaService {

    PersonaResponseDTO guardarPersona(PersonaRequestDTO dto);

    PersonaResponseDTO actualizarPersona(PersonaRequestDTO dto, Long id);

    List<PersonaResponseDTO> listarPersonas();

    List<PersonaResponseDTO> buscarMayorQueEdad(Integer edad);

    PersonaResponseDTO buscarPorId(Long id);

    void eliminarPersona(Long id);
}