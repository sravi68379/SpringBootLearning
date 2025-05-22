// src/main/java/com/ravi/LibraryManagementDTO/mapper/AuthorMapper.java
package com.ravi.LibraryManagementDTO.mapper;

import com.ravi.LibraryManagementDTO.model.Author;
import com.ravi.LibraryManagementDTO.model.Book; // Required for accessing book.getTitle()
import com.ravi.LibraryManagementDTO.dto.AuthorDTO;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.Collections;

@Component
public class AuthorMapper {


    public AuthorMapper() {}

    public AuthorDTO toDto(Author author) {
        if (author == null) {
            return null;
        }
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        authorDTO.setBio(author.getBio());

        if (author.getBooks() != null && !author.getBooks().isEmpty()) {
            authorDTO.setWrittenBooks(author.getBooks().stream()
                    .map(Book::getTitle) // Extract title directly
                    .collect(Collectors.toSet()));
        } else {
            authorDTO.setWrittenBooks(Collections.emptySet());
        }
        return authorDTO;
    }

    public Author toEntity(AuthorDTO authorDTO) {
        if (authorDTO == null) {
            return null;
        }
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setName(authorDTO.getName());
        author.setBio(authorDTO.getBio());
        return author;
    }

    public Set<AuthorDTO> toDtoSet(Set<Author> authors) {
        if (authors == null) {
            return Collections.emptySet();
        }
        return authors.stream().map(this::toDto).collect(Collectors.toSet());
    }
}