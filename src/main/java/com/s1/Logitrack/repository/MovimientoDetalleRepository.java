package com.s1.Logitrack.repository;

import com.s1.Logitrack.model.MovimientoDetalles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientoDetalleRepository extends JpaRepository<MovimientoDetalles, Long> {
    List<MovimientoDetalles> findByMovimientoId(Long movimientoId);
    List<MovimientoDetalles> findByProductoId(Long productoId);
}