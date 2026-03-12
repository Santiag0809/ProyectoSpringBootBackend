package com.s1.Logitrack.repository;

import com.s1.Logitrack.model.Movimientos;
import com.s1.Logitrack.model.TipoMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimientos, Long> {
    List<Movimientos> findByTipoMovimiento(TipoMovimiento tipoMovimiento);
    List<Movimientos> findByUsuarioId(Long usuarioId);
    List<Movimientos> findByBodegaOrigenId(Long bodegaOrigenId);
    List<Movimientos> findByBodegaDestinoId(Long bodegaDestinoId);
}