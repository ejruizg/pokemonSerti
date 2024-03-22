package com.example.pokemonSerti.model.responseService.evoluciones;

import com.example.pokemonSerti.model.responseService.PokemonSpecies;
import lombok.Data;

import java.util.List;

@Data
public class ChainLink {
    private boolean is_baby;
    private PokemonSpecies species;
    private EvolutionDetail evolutionDetail;
    private List<ChainLink> evolves_to;
}
