package com.s1.Logitrack.service.impl;

import com.s1.Logitrack.dto.request.AuditoriaRequestDTO;
import com.s1.Logitrack.dto.response.AuditoriaResponseDTO;
import com.s1.Logitrack.dto.response.PersonaResponseDTO;
import com.s1.Logitrack.mapper.AuditoriaMapper;
import com.s1.Logitrack.mapper.PersonaMapper;
import com.s1.Logitrack.model.Auditorias;
import com.s1.Logitrack.model.Persona;
import com.s1.Logitrack.model.TipoOperacion;
import com.s1.Logitrack.repository.AuditoriaRepository;
import com.s1.Logitrack.repository.PersonaRepository;
import com.s1.Logitrack.service.AuditoriaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditoriaServiceImpl implements AuditoriaService {

    private final AuditoriaRepository auditoriaRepository;
    private final AuditoriaMapper auditoriaMapper;
    private final PersonaRepository personaRepository;
    private final PersonaMapper personaMapper;

    private AuditoriaResponseDTO toDTO(Auditorias a) {
        PersonaResponseDTO usuarioDTO = personaMapper.entidadADTO(a.getUsuario());
        return auditoriaMapper.entidadADTO(a, usuarioDTO);
    }

    @Override
    public AuditoriaResponseDTO guardarAuditoria(AuditoriaRequestDTO dto) {
        Persona usuario = personaRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new EntityNotFoundException("No existe el usuario con id: " + dto.usuarioId()));
        Auditorias a = auditoriaMapper.DTOAEntidad(dto, usuario);
        a.setFecha(LocalDateTime.now());
        return toDTO(auditoriaRepository.save(a));
    }

    @Override
    public List<AuditoriaResponseDTO> listarAuditorias() {
        return auditoriaRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public AuditoriaResponseDTO buscarPorId(Long id) {
        Auditorias a = auditoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe la auditoría con id: " + id));
        return toDTO(a);
    }

    @Override
    public List<AuditoriaResponseDTO> buscarPorEntidad(String entidad) {
        return auditoriaRepository.findByEntidadIgnoreCase(entidad).stream().map(this::toDTO).toList();
    }

    @Override
    public List<AuditoriaResponseDTO> buscarPorOperacion(TipoOperacion operacion) {
        return auditoriaRepository.findByOperacion(operacion).stream().map(this::toDTO).toList();
    }

    @Override
    public List<AuditoriaResponseDTO> buscarPorUsuario(Long usuarioId) {
        return auditoriaRepository.findByUsuarioId(usuarioId).stream().map(this::toDTO).toList();
    }

    @Override
    public void eliminarAuditoria(Long id) {
        Auditorias a = auditoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe la auditoría con id: " + id));
        auditoriaRepository.delete(a);
    }
}
