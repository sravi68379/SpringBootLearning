package com.ravi.MovieManagementSystem.repository;

import com.ravi.MovieManagementSystem.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByDirectorContainingIgnoreCase(String directorName);

    List<Movie> findByTitleContainingIgnoreCase(String title);

    List<Movie> findByRatingGreaterThanEqual(Double rating);
}