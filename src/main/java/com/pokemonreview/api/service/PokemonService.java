package com.pokemonreview.api.service;

import com.pokemonreview.api.dto.PokemonDTO;

import java.util.List;

public interface PokemonService {
    PokemonDTO createPokemon(PokemonDTO pokemonDTO);
    List<PokemonDTO> getAllPokemon();
}
