package com.example.pokemonSerti.service;

import com.example.pokemonSerti.entity.Consulta;
import com.example.pokemonSerti.entity.Evolucion;
import com.example.pokemonSerti.entity.Movimiento;
import com.example.pokemonSerti.model.responseService.Move;
import com.example.pokemonSerti.model.responseService.Pokemon;
import com.example.pokemonSerti.model.ResponsePokemon;
import com.example.pokemonSerti.model.responseService.PokemonMove;
import com.example.pokemonSerti.model.responseService.evoluciones.ChainLink;
import com.example.pokemonSerti.model.responseService.evoluciones.PokemonChainsService;
import com.example.pokemonSerti.model.responseService.PokemonSpecies;
import com.example.pokemonSerti.repository.ConsultaRepository;
import com.google.gson.Gson;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class PokemonServiceImpl implements PokemonService{
    @Value( "${api.pokemon.especies.url}")
    private String urlPokemonEspeciesApi;
    @Value( "${api.pokemon.api.url}")
    private String urlPokemonApi;

    @Autowired
    private ConsultaRepository consultaRepository;
    @Override
    public PokemonSpecies getPokemonSpecies(int id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(urlPokemonEspeciesApi +id, String.class);
        Gson gson = new Gson();
        PokemonSpecies pokemon = gson.fromJson(responseEntity.getBody().toString(), PokemonSpecies.class);
        //Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        return pokemon;
    }
    @Override
    public Pokemon getPokemonData(int id){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(urlPokemonApi +id, String.class);
        Gson gson = new Gson();
        Pokemon pokemon = gson.fromJson(responseEntity.getBody().toString(), Pokemon.class);
        //Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        return pokemon;
    }

    @Override
    public ResponsePokemon getPokemonForApi(int id){
        ResponsePokemon rp= new ResponsePokemon();
        PokemonSpecies pe = getPokemonSpecies(id);// se consume el servicio de especies-> nos devuelve las evoluciones
        Pokemon p= getPokemonData(id);// se consume el servicio de pokemon -> nos devuelve la informacion del pokemon

        List evoluciones = new ArrayList<>();

        Pokemon pData=getPokemonData(id);
        rp.setNombre(pe.getName());
        rp.setColor(pe.getColor().getName());
        rp.setPeso(pData.getWeight());
        rp.setAltura(pData.getHeight());
        rp.setImagen(pData.getSprites().getFront_default());

        if(pe.getEvolution_chain()!=null){
            evoluciones = getEvoluciones(pe.getEvolution_chain().getUrl());//se consulta servicio de evoluciones
        }

        rp.setEvoluciones(evoluciones);
        rp.setMoves(getMovesPokemon(p.getMoves()));
        guardarConsulta(rp);
        return rp;
    }



    private List getEvoluciones(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        Gson gson = new Gson();
        PokemonChainsService pokemonChains = gson.fromJson(responseEntity.getBody().toString(), PokemonChainsService.class);
        //Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        ArrayList evoluciones = new ArrayList<>();
        obtenerNombresEvoluciones(pokemonChains.getChain(),evoluciones);

        return evoluciones;
    }

    private static void obtenerNombresEvoluciones(ChainLink chainLink,ArrayList evoluciones) {
        if (chainLink != null) {
            PokemonSpecies species = chainLink.getSpecies();
            if (species != null) {
                evoluciones.add( species.getName());
            }
            List<ChainLink> evolvesTo = chainLink.getEvolves_to();
            if (evolvesTo != null && !evolvesTo.isEmpty()) {
                for (ChainLink link : evolvesTo) {
                    obtenerNombresEvoluciones(link,evoluciones);
                }
            }
        }
    }

    private static ArrayList getMovesPokemon(List<PokemonMove> movesList) {
        ArrayList moves = new ArrayList<>();
        if(movesList != null && !movesList.isEmpty()){
            for(PokemonMove m:movesList){
                moves.add(m.getMove().getName());
            }
        }
        return moves;
    }

    private int guardarConsulta(ResponsePokemon responsePokemon){
        Consulta consulta= new Consulta();

        com.example.pokemonSerti.entity.Pokemon pokemonEntity = new com.example.pokemonSerti.entity.Pokemon();
        Set<Evolucion> evolucionList = new HashSet<Evolucion>();
        Set<Movimiento> movimientoList = new HashSet<Movimiento>();
        Evolucion evolucion;
        Movimiento movimiento;
        for(String evo :responsePokemon.getEvoluciones()){
            evolucion=new Evolucion();
            evolucion.setNombre(evo);
            evolucionList.add(evolucion);
        }
        for(String mov :responsePokemon.getMoves()){
            movimiento=new Movimiento();
            movimiento.setNombre(mov);
            movimientoList.add(movimiento);
        }

        pokemonEntity.setNombre(responsePokemon.getNombre());
        pokemonEntity.setImagen(responsePokemon.getImagen());
        pokemonEntity.setAltura(responsePokemon.getAltura());
        pokemonEntity.setPeso(responsePokemon.getPeso());
        pokemonEntity.setImagen(responsePokemon.getImagen());
        pokemonEntity.setEvoluciones(evolucionList);
        pokemonEntity.setMovimientos(movimientoList);
        pokemonEntity.setColor(responsePokemon.getColor());

        consulta.setPokemon(pokemonEntity);
        consulta.setFecha_consulta( new Timestamp(System.currentTimeMillis()));

        Consulta consultaInsert=consultaRepository.save(consulta);
        if(consultaInsert==null){
            System.out.println("null");
        }else{
            System.out.println(consultaInsert);
        }



        return 0;
    }
}
