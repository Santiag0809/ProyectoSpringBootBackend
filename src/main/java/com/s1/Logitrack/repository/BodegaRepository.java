package com.s1.Logitrack.repository;

import com.s1.Logitrack.model.Bodegas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BodegaRepository extends JpaRepository<Bodegas, Long> {
    List<Bodegas> findByNombreIgnoreCase(String nombre);
    List<Bodegas> findByEncargadoId(Long encargadoId);
    boolean existsByNombre(String nombre);
}
