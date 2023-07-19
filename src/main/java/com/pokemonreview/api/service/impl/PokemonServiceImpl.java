package com.pokemonreview.api.service.impl;

import com.pokemonreview.api.dto.PokemonDTO;
import com.pokemonreview.api.exceptions.PokemonNotFoundException;
import com.pokemonreview.api.models.PokemonEntity;
import com.pokemonreview.api.repository.PokemonRepository;
import com.pokemonreview.api.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {
    private PokemonRepository pokemonRepository;
    @Autowired
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public PokemonDTO createPokemon(PokemonDTO pokemonDTO) {
        PokemonEntity pokemon = new PokemonEntity();
        pokemon.setName(pokemonDTO.getName());
        pokemon.setType(pokemonDTO.getType());

        PokemonEntity newPokemon = pokemonRepository.save(pokemon);

        PokemonDTO pokemonResponse = new PokemonDTO();
        pokemonResponse.setId(newPokemon.getId());
        pokemonResponse.setName(newPokemon.getName());
        pokemonResponse.setType(newPokemon.getType());
        return pokemonResponse;
    }

    @Override
    public List<PokemonDTO> getAllPokemon() {
        List<PokemonEntity> pokemon = pokemonRepository.findAll();
        return pokemon.stream().map(p -> mapToDTO(p)).collect(Collectors.toList());
    }

    @Override
    public PokemonDTO getPokemonById(int id) {
        PokemonEntity pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be found"));
        return mapToDTO(pokemon);
    }

    @Override
    public PokemonDTO updatePokemon(PokemonDTO pokemonDTO, int id) {
        PokemonEntity pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be updated"));

        pokemon.setName(pokemonDTO.getName());
        pokemon.setType(pokemon.getType());

        PokemonEntity updatedPokemon = pokemonRepository.save(pokemon);
        return mapToDTO(updatedPokemon);
    }

    @Override
    public void deletePokemon(int id) {
        PokemonEntity pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be deleted"));
        pokemonRepository.delete(pokemon);
    }

    private PokemonDTO mapToDTO(PokemonEntity pokemon){
        PokemonDTO pokemonDTO = new PokemonDTO();
        pokemonDTO.setId(pokemon.getId());
        pokemonDTO.setName(pokemon.getName());
        pokemonDTO.setType(pokemon.getType());
        return pokemonDTO;
    }

    private PokemonEntity mapToEntity(PokemonDTO pokemonDTO){
        PokemonEntity pokemon = new PokemonEntity();
        pokemon.setName(pokemonDTO.getName());
        pokemon.setType(pokemonDTO.getType());
        return pokemon;
    }
}
