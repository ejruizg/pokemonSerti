package com.example.pokemonSerti.controller;

import com.example.pokemonSerti.model.ResponsePokemon;
import com.example.pokemonSerti.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PokemonController {
    @Autowired
    private PokemonService pokemonService;

    @GetMapping("/pokemon/{id}")
    ResponseEntity <ResponsePokemon> getPokemon(@PathVariable int id){
        ResponsePokemon response= pokemonService.getPokemonForApi(id);
        return ResponseEntity.ok(response);
    }
}
