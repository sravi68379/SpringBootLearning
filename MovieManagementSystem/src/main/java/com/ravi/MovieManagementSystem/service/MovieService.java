package com.ravi.MovieManagementSystem.service;

import com.ravi.MovieManagementSystem.dto.MovieDTO;
import java.util.List;
import java.util.Optional;

public interface MovieService {
    MovieDTO createMovie(MovieDTO movieDTO);
    List<MovieDTO> getAllMovies();
    Optional<MovieDTO> getMovieById(Long id);
    Optional<MovieDTO> updateMovie(Long id, MovieDTO movieDTO);
    boolean deleteMovie(Long id);
    List<MovieDTO> searchMoviesByDirector(String directorName);
    List<MovieDTO> searchMoviesByTitle(String title);
    List<MovieDTO> searchMoviesByMinimumRating(Double rating);
}