// src/main/java/com/ravi/LibraryManagementDTO/dto/AuthorDTO.java
package com.ravi.LibraryManagementDTO.dto;

import java.util.Set;
import java.util.HashSet;

public class AuthorDTO {
    private Long id;
    private String name;
    private String bio;
    private Set<String> writtenBooks = new HashSet<>(); // Changed to Set<String>

    public AuthorDTO() {}

    public AuthorDTO(Long id, String name, String bio) {
        this.id = id;
        this.name = name;
        this.bio = bio;
    }

    public AuthorDTO(Long id, String name, String bio, Set<String> writtenBooks) {
        this.id = id;
        this.name = name;
        this.bio = bio;
        this.writtenBooks = writtenBooks;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Set<String> getWrittenBooks() {
        return writtenBooks;
    }

    public void setWrittenBooks(Set<String> writtenBooks) {
        this.writtenBooks = writtenBooks;
    }
}