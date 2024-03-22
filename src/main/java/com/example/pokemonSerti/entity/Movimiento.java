package com.example.pokemonSerti.entity;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "movimiento")
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movimiento_seq")
    @SequenceGenerator(name = "movimiento_seq", sequenceName = "MOVIMIENTO_SEQ", allocationSize = 1)
    @Column(name = "id_movimiento")
    private Long idMovimiento;
    private String nombre;
}
