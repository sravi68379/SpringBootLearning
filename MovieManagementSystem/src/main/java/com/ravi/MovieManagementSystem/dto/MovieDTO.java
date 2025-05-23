package com.ravi.MovieManagementSystem.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public class MovieDTO {

    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Director cannot be blank")
    private String director;

    private LocalDate releaseDate;

    @DecimalMin(value = "0.0", message = "Rating must be between 0.0 and 10.0")
    @DecimalMax(value = "10.0", message = "Rating must be between 0.0 and 10.0")
    private Double rating;

    public MovieDTO() {
    }

    public MovieDTO(Long id, String title, String director, LocalDate releaseDate, Double rating) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

     @Override
    public String toString() {
        return "MovieDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", releaseDate=" + releaseDate +
                ", rating=" + rating +
                '}';
    }
}