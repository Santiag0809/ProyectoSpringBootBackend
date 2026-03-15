package com.s1.Logitrack.mapper;

import com.s1.Logitrack.dto.request.AuditoriaRequestDTO;
import com.s1.Logitrack.dto.response.AuditoriaResponseDTO;
import com.s1.Logitrack.dto.response.PersonaResponseDTO;
import com.s1.Logitrack.model.Auditorias;
import com.s1.Logitrack.model.Persona;
import org.springframework.stereotype.Component;

@Component
public class AuditoriaMapper {

    public AuditoriaResponseDTO entidadADTO(Auditorias auditoria, PersonaResponseDTO usuarioDTO) {
        if (auditoria == null || usuarioDTO == null) return null;
        return new AuditoriaResponseDTO(
                auditoria.getId(),
                auditoria.getEntidad(),
                auditoria.getOperacion(),
                auditoria.getFecha(),
                usuarioDTO,
                auditoria.getValorAnterior(),
                auditoria.getValorNuevo()
        );
    }

    public Auditorias DTOAEntidad(AuditoriaRequestDTO dto, Persona usuario) {
        if (dto == null || usuario == null) return null;
        Auditorias a = new Auditorias();
        a.setEntidad(dto.entidad());
        a.setOperacion(dto.operacion());
        a.setUsuario(usuario);
        a.setValorAnterior(dto.valorAnterior());
        a.setValorNuevo(dto.valorNuevo());
        return a;
    }

    public void actualizarEntidadDesdeDTO(Auditorias auditoria, AuditoriaRequestDTO dto, Persona usuario) {
        if (auditoria == null || dto == null || usuario == null) return;
        auditoria.setEntidad(dto.entidad());
        auditoria.setOperacion(dto.operacion());
        auditoria.setUsuario(usuario);
        auditoria.setValorAnterior(dto.valorAnterior());
        auditoria.setValorNuevo(dto.valorNuevo());
    }
}
