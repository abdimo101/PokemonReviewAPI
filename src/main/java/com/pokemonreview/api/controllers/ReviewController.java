package com.pokemonreview.api.controllers;

import com.pokemonreview.api.dto.ReviewDTO;
import com.pokemonreview.api.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ReviewController {

    private ReviewService reviewService;
    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/pokemon/{pokemonId}/review")
    public ResponseEntity<ReviewDTO> createReview(@PathVariable(value = "pokemonId") int pokemonId, @RequestBody ReviewDTO reviewDTO){
        return new ResponseEntity<>(reviewService.createReview(pokemonId, reviewDTO), HttpStatus.CREATED);
    }

    @GetMapping("/pokemon/{pokemonId}/reviews")
    public List<ReviewDTO> getReviewsByPokemonId(@PathVariable(value = "pokemonId") int pokemonId){
        return reviewService.getReviewsByPokemonId(pokemonId);
    }
}
