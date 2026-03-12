package com.s1.Logitrack.mapper;

import com.s1.Logitrack.dto.request.BodegaRequestDTO;
import com.s1.Logitrack.dto.response.BodegaResponseDTO;
import com.s1.Logitrack.dto.response.PersonaResponseDTO;
import com.s1.Logitrack.model.Bodegas;
import com.s1.Logitrack.model.Persona;
import org.springframework.stereotype.Component;

@Component
public class BodegaMapper {

    public BodegaResponseDTO entidadADTO(Bodegas bodega, PersonaResponseDTO encargadoDTO) {
        if (bodega == null || encargadoDTO == null) return null;
        return new BodegaResponseDTO(
                bodega.getId(),
                bodega.getNombre(),
                bodega.getUbicacion(),
                bodega.getCapacidad(),
                encargadoDTO
        );
    }

    public Bodegas DTOAEntidad(BodegaRequestDTO dto, Persona encargado) {
        if (dto == null || encargado == null) return null;
        Bodegas b = new Bodegas();
        b.setNombre(dto.nombre());
        b.setUbicacion(dto.ubicacion());
        b.setCapacidad(dto.capacidad());
        b.setEncargado(encargado);
        return b;
    }

    public void actualizarEntidadDesdeDTO(Bodegas bodega, BodegaRequestDTO dto, Persona encargado) {
        if (bodega == null || dto == null || encargado == null) return;
        bodega.setNombre(dto.nombre());
        bodega.setUbicacion(dto.ubicacion());
        bodega.setCapacidad(dto.capacidad());
        bodega.setEncargado(encargado);
    }
}