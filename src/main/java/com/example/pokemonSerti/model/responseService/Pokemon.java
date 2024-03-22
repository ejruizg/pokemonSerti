package com.example.pokemonSerti.model.responseService;

import lombok.Data;

import java.util.List;

@Data
public class Pokemon {
    private int id;
    private String name;
    private int base_experience;
    private int height;
    private boolean is_default;
    private int weight;
    private List <PokemonMove> moves ;
    private Sprites sprites;

}
