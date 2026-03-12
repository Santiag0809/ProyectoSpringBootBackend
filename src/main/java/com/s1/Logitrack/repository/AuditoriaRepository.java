package com.s1.Logitrack.repository;

import com.s1.Logitrack.model.Auditorias;
import com.s1.Logitrack.model.TipoOperacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditoriaRepository extends JpaRepository<Auditorias, Long> {
    List<Auditorias> findByEntidadIgnoreCase(String entidad);
    List<Auditorias> findByOperacion(TipoOperacion operacion);
    List<Auditorias> findByUsuarioId(Long usuarioId);
}