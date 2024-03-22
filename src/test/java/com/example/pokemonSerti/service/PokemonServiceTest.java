
package com.example.pokemonSerti.service;


import com.example.pokemonSerti.controller.PokemonController;
import com.example.pokemonSerti.model.ResponsePokemon;
import com.example.pokemonSerti.model.responseService.*;
import com.example.pokemonSerti.model.responseService.evoluciones.ChainLink;
import com.example.pokemonSerti.model.responseService.evoluciones.EvolutionDetail;
import com.example.pokemonSerti.model.responseService.evoluciones.PokemonChainsService;
import com.example.pokemonSerti.repository.ConsultaRepository;
import com.example.pokemonSerti.service.PokemonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PokemonServiceTest {




    @Mock
    private ConsultaRepository consultaRepository;
    @Mock
    private RestTemplate restTemplate;
    @Value("${api.pokemon.especies.url}")
    private String urlPokemonEspeciesApi;

    @Value( "${api.pokemon.api.url}")
    private String urlPokemonApi;
    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(pokemonService, "urlPokemonEspeciesApi", "https://pokeapi.co/api/v2/pokemon-species/");
        ReflectionTestUtils.setField(pokemonService, "urlPokemonApi", "https://pokeapi.co/api/v2/pokemon/");
    }
    @InjectMocks
    private PokemonServiceImpl pokemonService;

    @Test
    void getPokemonSpecies() throws URISyntaxException {

        int pokemonId = 25;
        URI uri = new URI(urlPokemonEspeciesApi+pokemonId);

        String expectedResponse = "{\n" +
                "  \"id\": 25,\n" +
                "  \"name\": \"pikachu\",\n" +
                "  \"order\": 26,\n" +
                "  \"gender_rate\": 4,\n" +
                "  \"capture_rate\": 190,\n" +
                "  \"base_happiness\": 50,\n" +
                "  \"is_baby\": false,\n" +
                "  \"is_legendary\": false,\n" +
                "  \"is_mythical\": false,\n" +
                "  \"hatch_counter\": 10,\n" +
                "  \"has_gender_differences\": true,\n" +
                "  \"forms_switchable\": false,\n" +
                "  \"evolution_chain\": \"https://pokeapi.co/api/v2/evolution-chain/10/\",\n" +
                "  \"color\": {\n" +
                "    \"name\": \"yellow\",\n" +
                "    \"url\": \"https://pokeapi.co/api/v2/pokemon-color/10/\"\n" +
                "  }\n" +
                "}";

        ResponseEntity<String> mockResponseEntity = new ResponseEntity<>(expectedResponse, HttpStatus.OK);
        PokemonSpecies pokemonEsperado = new PokemonSpecies();
        pokemonEsperado.setName("pikachu");
        pokemonEsperado.setOrder(26);
        pokemonEsperado.setGender_rate(4);
        pokemonEsperado.setCapture_rate(190);
        pokemonEsperado.setBase_happiness(50);
        pokemonEsperado.set_baby(false);
        pokemonEsperado.set_legendary(false);
        pokemonEsperado.set_mythical(false);
        pokemonEsperado.setHatch_counter(10);
        pokemonEsperado.setHas_gender_differences(true);
        pokemonEsperado.setForms_switchable(false);
        Evolution_chain e = new Evolution_chain();
        e.setUrl("https://pokeapi.co/api/v2/evolution-chain/10/");
        pokemonEsperado.setEvolution_chain(e);
        Color c=new Color();
        c.setName("yellow");
        c.setUrl("https://pokeapi.co/api/v2/pokemon-color/10/");
        pokemonEsperado.setColor(c);
        pokemonEsperado.setId(25);



        when(restTemplate.getForEntity(uri, String.class)).thenReturn(mockResponseEntity);
        PokemonSpecies actualResponseEntity = pokemonService.getPokemonSpecies(pokemonId);

        assertEquals(pokemonEsperado, actualResponseEntity);

    }


    @Test
    void getPokemonData() throws URISyntaxException, JsonProcessingException {

        int pokemonId = 25;
        URI uri = new URI(urlPokemonApi+pokemonId);

        String expectedResponse = "{\n" +
                "  \"id\": 25,\n" +
                "  \"name\": \"pikachu\"\n}" ;

        ResponseEntity<String> mockResponseEntity = new ResponseEntity<>(expectedResponse, HttpStatus.OK);
        Pokemon pokemonEsperado = new Pokemon();
        pokemonEsperado.setName("pikachu");
        pokemonEsperado.set_default(false);
        pokemonEsperado.setBase_experience(112);
        pokemonEsperado.setHeight(4);
        pokemonEsperado.set_default(true);
        pokemonEsperado.setWeight(60);
        List<PokemonMove> moves=new ArrayList<PokemonMove>();
        Move m= new Move();m.setName("mega-punch");
        PokemonMove pm=new PokemonMove();pm.setMove(m);
        moves.add(pm);
        pokemonEsperado.setId(25);



        when(restTemplate.getForEntity(uri, String.class)).thenReturn(mockResponseEntity);
        Pokemon actualResponseEntity = pokemonService.getPokemonData(pokemonId);

        assertEquals(pokemonEsperado.getName(), actualResponseEntity.getName());

    }

    @Test
    void getPokemonForApi() throws URISyntaxException, JsonProcessingException {

        int pokemonId = 25;
        URI uri = new URI("https://pokeapi.co/api/v2/evolution-chain/10/");

        String expectedResponse = "{\n" +
                "  \"id\": 25,\n" +
                "  \"name\": \"pikachu\"\n}" ;

        ResponseEntity<String> mockResponseEntity = new ResponseEntity<>(expectedResponse, HttpStatus.OK);
        ResponsePokemon pokemonEsperado = new ResponsePokemon();
        pokemonEsperado.setNombre("pikachu");
 /*       pokemonEsperado.setId(25);
        ChainLink chain = new ChainLink();
        EvolutionDetail evo=new EvolutionDetail();
        PokemonSpecies ps=new PokemonSpecies();
        ps.setName("raichu");
        chain.setSpecies(ps);
        pokemonEsperado.setChain(chain);*/


        when(restTemplate.getForEntity(uri, String.class)).thenReturn(mockResponseEntity);
        ResponsePokemon actualResponseEntity = pokemonService.getPokemonForApi(pokemonId);

        assertEquals(pokemonEsperado.getNombre(), actualResponseEntity.getNombre());

    }
}

