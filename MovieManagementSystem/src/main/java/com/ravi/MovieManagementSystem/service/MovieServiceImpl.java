package com.ravi.MovieManagementSystem.service;

import com.ravi.MovieManagementSystem.domain.Movie;
import com.ravi.MovieManagementSystem.dto.MovieDTO;
import com.ravi.MovieManagementSystem.repository.MovieRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    private MovieDTO convertToDTO(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        BeanUtils.copyProperties(movie, movieDTO);
        return movieDTO;
    }

    private Movie convertToEntity(MovieDTO movieDTO) {
        Movie movie = new Movie();
        BeanUtils.copyProperties(movieDTO, movie);
        return movie;
    }

    @Override
    public MovieDTO createMovie(MovieDTO movieDTO) {
        Movie movie = convertToEntity(movieDTO);
        Movie savedMovie = movieRepository.save(movie);
        return convertToDTO(savedMovie);
    }

    @Override
    public List<MovieDTO> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MovieDTO> getMovieById(Long id) {
        return movieRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public Optional<MovieDTO> updateMovie(Long id, MovieDTO movieDTO) {
        return movieRepository.findById(id)
                .map(existingMovie -> {
                    existingMovie.setTitle(movieDTO.getTitle());
                    existingMovie.setDirector(movieDTO.getDirector());
                    existingMovie.setReleaseDate(movieDTO.getReleaseDate());
                    existingMovie.setRating(movieDTO.getRating());
                    Movie updatedMovie = movieRepository.save(existingMovie);
                    return convertToDTO(updatedMovie);
                });
    }

    @Override
    public boolean deleteMovie(Long id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<MovieDTO> searchMoviesByDirector(String directorName) {
        return movieRepository.findByDirectorContainingIgnoreCase(directorName).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDTO> searchMoviesByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDTO> searchMoviesByMinimumRating(Double rating) {
        return movieRepository.findByRatingGreaterThanEqual(rating).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}