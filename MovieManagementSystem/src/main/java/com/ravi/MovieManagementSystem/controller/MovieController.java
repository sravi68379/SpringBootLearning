package com.ravi.MovieManagementSystem.controller;

import com.ravi.MovieManagementSystem.dto.MovieDTO;
import com.ravi.MovieManagementSystem.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<MovieDTO> createMovie(@Valid @RequestBody MovieDTO movieDTO) {
        MovieDTO createdMovie = movieService.createMovie(movieDTO);
        return new ResponseEntity<>(createdMovie, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        List<MovieDTO> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable Long id) {
        Optional<MovieDTO> movieDTO = movieService.getMovieById(id);
        return movieDTO.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> updateMovie(@PathVariable Long id, @Valid @RequestBody MovieDTO movieDTO) {
        Optional<MovieDTO> updatedMovie = movieService.updateMovie(id, movieDTO);
        return updatedMovie.map(ResponseEntity::ok)
                           .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        boolean deleted = movieService.deleteMovie(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search/director")
    public ResponseEntity<List<MovieDTO>> searchMoviesByDirector(@RequestParam String name) {
        List<MovieDTO> movies = movieService.searchMoviesByDirector(name);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/search/title")
    public ResponseEntity<List<MovieDTO>> searchMoviesByTitle(@RequestParam String title) {
        List<MovieDTO> movies = movieService.searchMoviesByTitle(title);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/search/rating")
    public ResponseEntity<List<MovieDTO>> searchMoviesByMinimumRating(@RequestParam Double min) {
        List<MovieDTO> movies = movieService.searchMoviesByMinimumRating(min);
        return ResponseEntity.ok(movies);
    }
}