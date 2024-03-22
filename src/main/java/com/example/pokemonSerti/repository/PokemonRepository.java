package com.example.pokemonSerti.repository;

import com.example.pokemonSerti.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon,Integer> {
}
