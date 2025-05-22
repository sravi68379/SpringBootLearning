package com.ravi.LibraryManagementDTO.service;

import com.ravi.LibraryManagementDTO.dto.AuthorDTO;
import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<AuthorDTO> getAllAuthors();
    Optional<AuthorDTO> getAuthorById(Long id);
    AuthorDTO createAuthor(AuthorDTO authorDTO);
    AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO);
    void deleteAuthor(Long id);
}