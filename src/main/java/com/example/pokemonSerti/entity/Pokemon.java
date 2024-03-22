package com.example.pokemonSerti.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
@Data
@Entity
@Table(name = "pokemon")
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pokemon_seq")
    @SequenceGenerator(name = "pokemon_seq", sequenceName = "POKEMON_SEQ", allocationSize = 1)
    @Column(name = "id_pokemon")
    private Long idPokemon;
    private String nombre;
    private String color;
    private int peso;
    private String imagen;
    private int altura;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "evoluciones",
            joinColumns = @JoinColumn(name = "id_pokemon"),
            inverseJoinColumns = @JoinColumn(name = "evolucion_id")
    )
    private Set<Evolucion> evoluciones = new HashSet<>();
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "movimientos",
            joinColumns = @JoinColumn(name = "id_pokemon"),
            inverseJoinColumns = @JoinColumn(name = "movimiento_id")
    )
    private Set <Movimiento> movimientos = new HashSet<>();
}



