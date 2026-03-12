package com.s1.Logitrack.service.impl;

import com.s1.Logitrack.dto.request.PersonaRequestDTO;
import com.s1.Logitrack.dto.response.PersonaResponseDTO;
import com.s1.Logitrack.mapper.PersonaMapper;
import com.s1.Logitrack.model.Persona;
import com.s1.Logitrack.repository.PersonaRepository;
import com.s1.Logitrack.service.PersonaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;
    private final PersonaMapper personaMapper = new PersonaMapper();

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public PersonaResponseDTO guardarPersona(PersonaRequestDTO dto) {

        Persona persona = personaMapper.DTOAEntidad(dto);

        Persona guardada = personaRepository.save(persona);

        return personaMapper.entidadADTO(guardada);
    }

    @Override
    public PersonaResponseDTO actualizarPersona(PersonaRequestDTO dto, Long id) {

        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        personaMapper.actualizarEntidadDesdeDTO(persona, dto);

        Persona actualizada = personaRepository.save(persona);

        return personaMapper.entidadADTO(actualizada);
    }

    @Override
    public List<PersonaResponseDTO> listarPersonas() {

        return personaRepository.findAll()
                .stream()
                .map(personaMapper::entidadADTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonaResponseDTO> buscarMayorQueEdad(Integer edad) {

        return personaRepository.findByEdadGreaterThan(edad)
                .stream()
                .map(personaMapper::entidadADTO)
                .collect(Collectors.toList());
    }

    @Override
    public PersonaResponseDTO buscarPorId(Long id) {

        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        return personaMapper.entidadADTO(persona);
    }

    @Override
    public void eliminarPersona(Long id) {

        personaRepository.deleteById(id);
    }
}
