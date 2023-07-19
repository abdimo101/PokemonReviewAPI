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
    public ResponseEntity<List<PokemonDTO>> getPokemons(){
        return new ResponseEntity<>(pokemonService.getAllPokemon(), HttpStatus.OK);
    }

    @GetMapping("pokemon/{id}")
    public ResponseEntity<PokemonDTO> pokemonGetId(@PathVariable int id) {
        return ResponseEntity.ok(pokemonService.getPokemonById(id));
    }

    @PostMapping("pokemon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDTO> createPokemon(@RequestBody PokemonDTO pokemonDTO){
        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDTO), HttpStatus.CREATED);
    }

    @PutMapping("pokemon/{id}/update")
    public ResponseEntity<PokemonDTO> updatePokemon(@RequestBody PokemonDTO pokemonDTO, @PathVariable("id") int pokemonId){
        PokemonDTO response = pokemonService.updatePokemon(pokemonDTO, pokemonId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("pokemon/{id}/delete")
    public ResponseEntity<String> deletePokemon(@PathVariable("id") int pokemonId){
        pokemonService.deletePokemon(pokemonId);
        return new ResponseEntity<>("Pokemon deleted", HttpStatus.OK);
    }


}
