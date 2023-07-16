package com.pokemonreview.api.controllers;


import com.pokemonreview.api.dto.PokemonDTO;
import com.pokemonreview.api.models.PokemonEntity;
import com.pokemonreview.api.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PokemonController {

    private PokemonService pokemonService;
    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("pokemon")
    public ResponseEntity<List<PokemonEntity>> getPokemons(){
        List<PokemonEntity> pokemons = new ArrayList<>();
        pokemons.add(new PokemonEntity(1, "Squirtle", "Water"));
        pokemons.add(new PokemonEntity(2, "Pikachu", "Electric"));
        pokemons.add(new PokemonEntity(3, "Charmander", "Fire"));
        return ResponseEntity.ok(pokemons);
    }

    @GetMapping("pokemon/{id}")
    public PokemonEntity pokemonGetId(@PathVariable int id) {
        return new PokemonEntity(id, "Squirtle", "Water");
    }

    @PostMapping("pokemon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDTO> createPokemon(@RequestBody PokemonDTO pokemonDTO){
        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDTO), HttpStatus.CREATED);
    }

    @PutMapping("pokemon/{id}/update")
    public ResponseEntity<PokemonEntity> updatePokemon(@RequestBody PokemonEntity pokemon,
                                                       @PathVariable("id") int pokemonId){
        System.out.println(pokemon.getName());
        System.out.println(pokemon.getType());
        return ResponseEntity.ok(pokemon);
    }

    @DeleteMapping("pokemon/{id}/delete")
    public ResponseEntity<String> deletePokemon(@PathVariable("id") int pokemonId){
        System.out.println(pokemonId);
        return ResponseEntity.ok("Pokemon deleted successfully");
    }


}
