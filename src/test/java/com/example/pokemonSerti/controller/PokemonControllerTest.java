package com.example.pokemonSerti.controller;

import com.example.pokemonSerti.model.ResponsePokemon;
import com.example.pokemonSerti.service.PokemonService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PokemonControllerTest {
    @Mock
    private PokemonService pokemonService;

    @InjectMocks
    private PokemonController pokemonController;

    @Test
    void getPokemon() {
        ResponsePokemon pokemon = new ResponsePokemon();
        pokemon.setNombre("pikachu");
        pokemon.setImagen("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png");
        pokemon.setColor("yellow");
        pokemon.setPeso(60);
        pokemon.setAltura(4);
        ArrayList<String>moves=new ArrayList<String>();
        moves.add("mega-punch");
        moves.add("pay-day");
        pokemon.setMoves(moves);
        ArrayList<String>evoluciones=new ArrayList<String>();
        evoluciones.add("pichu");
        evoluciones.add("raichu");
        evoluciones.add("pikachu");
        pokemon.setEvoluciones(evoluciones);
        when(pokemonService.getPokemonForApi(25)).thenReturn(pokemon);

        ResponseEntity<ResponsePokemon> response = pokemonController.getPokemon(25);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pokemon, response.getBody());


    }
}
