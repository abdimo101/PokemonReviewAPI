package com.pokemonreview.api.repository;

import com.pokemonreview.api.models.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {
    List<ReviewEntity> findByPokemonId(int pokemonId);
}
