package com.example.pokemonSerti.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "evolucion")
public class Evolucion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "evolucion_seq")
    @SequenceGenerator(name = "evolucion_seq", sequenceName = "EVOLUCION_SEQ", allocationSize = 1)
    @Column(name = "id_evolucion")
    private Long idEvolucion;
    private String nombre;
}
