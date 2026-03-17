package com.s1.Logitrack.repository;

import com.s1.Logitrack.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    List<Persona> findByNombreIgnoreCase(String nombre);
    List<Persona> findByEdadGreaterThan(Integer edad);
    Optional<Persona> findByEmail(String email);
}

