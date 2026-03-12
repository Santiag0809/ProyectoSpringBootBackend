package com.s1.Logitrack.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "movimiento_detalles",
        uniqueConstraints = @UniqueConstraint(columnNames = {"movimiento_id", "producto_id"}))
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class MovimientoDetalles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movimiento_id", nullable = false)
    private Movimientos movimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Productos producto;

    @Column(nullable = false)
    private Integer cantidad;
}