package com.example.pokemonSerti.model.responseService;

import lombok.Data;

@Data
public class PokemonSpecies {
    private int id;
    private String name;
    private int order;
    private int gender_rate;
    private int capture_rate;
    private int base_happiness;
    private boolean is_baby;
    private boolean is_legendary;
    private boolean is_mythical;
    private int hatch_counter;
    private boolean has_gender_differences;
    private boolean forms_switchable;
    private Evolution_chain evolution_chain;
    private Color color;
}
