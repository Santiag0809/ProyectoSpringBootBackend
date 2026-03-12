package com.s1.Logitrack.repository;

import com.s1.Logitrack.model.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Productos, Long> {
    List<Productos> findByCategoriaIgnoreCase(String categoria);
    boolean existsByNombre(String nombre);
}
