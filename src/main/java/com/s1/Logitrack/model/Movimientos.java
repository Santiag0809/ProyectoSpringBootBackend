package com.s1.Logitrack.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Movimientos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimiento", nullable = false)
    private TipoMovimiento tipoMovimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Persona usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bodega_origen_id")
    private Bodegas bodegaOrigen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bodega_destino_id")
    private Bodegas bodegaDestino;
}