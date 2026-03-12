package com.s1.Logitrack.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bodegas")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Bodegas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String ubicacion;

    @Column(nullable = false)
    private Integer capacidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "encargado_id", nullable = false)
    private Persona encargado;
}