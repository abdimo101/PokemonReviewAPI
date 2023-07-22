package com.pokemonreview.api.service.impl;

import com.pokemonreview.api.dto.PokemonDTO;
import com.pokemonreview.api.dto.ReviewDTO;
import com.pokemonreview.api.exceptions.PokemonNotFoundException;
import com.pokemonreview.api.models.PokemonEntity;
import com.pokemonreview.api.models.ReviewEntity;
import com.pokemonreview.api.repository.PokemonRepository;
import com.pokemonreview.api.repository.ReviewRepository;
import com.pokemonreview.api.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private PokemonRepository pokemonRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, PokemonRepository pokemonRepository) {
        this.reviewRepository = reviewRepository;
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public ReviewDTO createReview(int pokemonId, ReviewDTO reviewDTO) {
        ReviewEntity review = mapToEntity(reviewDTO);

        PokemonEntity pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be found"));

        review.setPokemon(pokemon);

        ReviewEntity newReview = reviewRepository.save(review);

        return mapToDTO(newReview);
    }

    @Override
    public List<ReviewDTO> getReviewsByPokemonId(int id) {
        List<ReviewEntity> reviews = reviewRepository.findByPokemonId(id);
        return reviews.stream().map(review -> mapToDTO(review)).collect(Collectors.toList());
    }

    private ReviewDTO mapToDTO(ReviewEntity review){
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setTitle(review.getTitle());
        reviewDTO.setContent(review.getContent());
        reviewDTO.setStars(review.getStars());
        return reviewDTO;
    }

    private ReviewEntity mapToEntity(ReviewDTO reviewDTO){
        ReviewEntity review = new ReviewEntity();
        review.setTitle(reviewDTO.getTitle());
        review.setContent(reviewDTO.getContent());
        review.setStars(reviewDTO.getStars());
        return review;
    }
}
