package com.pokemonreview.api.service;

import com.pokemonreview.api.dto.PokemonDTO;
import com.pokemonreview.api.dto.PokemonResponse;

import java.util.List;

public interface PokemonService {
    PokemonDTO createPokemon(PokemonDTO pokemonDTO);
    PokemonResponse getAllPokemon(int pageNo, int pageSize);
    PokemonDTO getPokemonById(int id);
    PokemonDTO updatePokemon(PokemonDTO pokemonDTO, int id);
    void deletePokemon(int id);
}
