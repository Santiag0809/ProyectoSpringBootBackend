package com.s1.Logitrack.mapper;

import com.s1.Logitrack.dto.request.PersonaRequestDTO;
import com.s1.Logitrack.dto.response.PersonaResponseDTO;
import com.s1.Logitrack.model.Persona;
import org.springframework.stereotype.Component;

@Component
public class PersonaMapper {

    public PersonaResponseDTO entidadADTO(Persona persona){
        if(persona == null) return null;

        return new PersonaResponseDTO(
                persona.getId(),
                persona.getNombre(),
                persona.getApellido(),
                persona.getEdad(),
                persona.getDocumento(),
                persona.getEmail()
        );
    }

    public Persona DTOAEntidad(PersonaRequestDTO dto){
        if(dto == null) return null;
        Persona p = new Persona();
        p.setNombre(dto.nombre());
        p.setApellido(dto.apellido());
        p.setEdad(dto.edad());
        p.setDocumento(dto.documento()); // ← esto faltaba
        p.setEmail(dto.email());
        p.setPassword(dto.password());
        return p;
    }

    public void actualizarEntidadDesdeDTO(Persona p, PersonaRequestDTO dto){
        if(dto == null || p == null) return;
        p.setNombre(dto.nombre());
        p.setApellido(dto.apellido());
        p.setEdad(dto.edad());
        p.setDocumento(dto.documento()); // ← esto faltaba
        p.setEmail(dto.email());
        p.setPassword(dto.password());
    }
}