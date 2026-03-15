package com.s1.Logitrack.service.impl;

import com.s1.Logitrack.dto.request.PersonaRequestDTO;
import com.s1.Logitrack.dto.response.PersonaResponseDTO;
import com.s1.Logitrack.mapper.PersonaMapper;
import com.s1.Logitrack.model.Persona;
import com.s1.Logitrack.repository.PersonaRepository;
import com.s1.Logitrack.service.PersonaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;
    private final PersonaMapper personaMapper;

    @Override
    public PersonaResponseDTO guardarPersona(PersonaRequestDTO dto) {
        Persona p = personaMapper.DTOAEntidad(dto);
        Persona guardada = personaRepository.save(p);
        return personaMapper.entidadADTO(guardada);
    }

    @Override
    public PersonaResponseDTO actualizarPersona(PersonaRequestDTO dto, Long id) {
        Persona p = personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe la persona con id: " + id));
        personaMapper.actualizarEntidadDesdeDTO(p, dto);
        Persona actualizada = personaRepository.save(p);
        return personaMapper.entidadADTO(actualizada);
    }

    @Override
    public void eliminarPersona(Long id) {
        Persona p = personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe la persona con id: " + id));
        personaRepository.delete(p);
    }

    @Override
    public List<PersonaResponseDTO> listarPersonas() {
        return personaRepository.findAll()
                .stream()
                .map(personaMapper::entidadADTO)
                .toList();
    }

    @Override
    public PersonaResponseDTO buscarPorId(Long id) {
        Persona p = personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe la persona con id: " + id));
        return personaMapper.entidadADTO(p);
    }

    @Override
    public List<PersonaResponseDTO> buscarMayorQueEdad(Integer edad) {
        return personaRepository.findByEdadGreaterThan(edad)
                .stream()
                .map(personaMapper::entidadADTO)
                .toList();
    }

}
