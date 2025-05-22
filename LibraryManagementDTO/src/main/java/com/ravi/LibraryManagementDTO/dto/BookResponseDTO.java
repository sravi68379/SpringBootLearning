// src/main/java/com/ravi/LibraryManagementDTO/dto/BookResponseDTO.java
package com.ravi.LibraryManagementDTO.dto;

import java.time.LocalDate;
import java.util.Set;
import java.util.HashSet;

public class BookResponseDTO {
    private Long id;
    private String title;
    private String isbn;
    private LocalDate publishedDate;
    private Set<String> authorNames = new HashSet<>(); // Changed to Set<String>
    private String borrowerName; // Changed to String

    // Getters and Setters
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Set<String> getAuthorNames() {
        return authorNames;
    }

    public void setAuthorNames(Set<String> authorNames) {
        this.authorNames = authorNames;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }
}