package com.example.pokemonSerti.service;

import com.example.pokemonSerti.model.ResponsePokemon;
import com.example.pokemonSerti.model.responseService.Pokemon;
import com.example.pokemonSerti.model.responseService.PokemonSpecies;

import java.util.Map;

public interface PokemonService {
    PokemonSpecies getPokemonSpecies(int id);
    Pokemon getPokemonData(int id);
    ResponsePokemon getPokemonForApi(int id);
}
