package com.example.pokemonSerti.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "consulta")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "consulta_seq")
    @SequenceGenerator(name = "consulta_seq", sequenceName = "CONSULTA_SEQ", allocationSize = 1)
    @Column(name = "id_consulta")
    private Long idConsulta;
    private Date fecha_consulta;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pokemon_id")
    private Pokemon pokemon;
}
