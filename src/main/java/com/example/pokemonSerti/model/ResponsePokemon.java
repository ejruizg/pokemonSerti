package com.example.pokemonSerti.model;

import com.example.pokemonSerti.model.responseService.PokemonMove;
import lombok.Data;

import java.util.List;

@Data
public class ResponsePokemon {
    private String nombre;
    private String color;
    private int peso;
    private String imagen;
    private int altura;
    private List<String> evoluciones;
    private List <String> moves ;



}
