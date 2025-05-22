package com.ravi.LibraryManagementDTO.service;

import com.ravi.LibraryManagementDTO.dto.BookRequestDTO;
import com.ravi.LibraryManagementDTO.dto.BookResponseDTO;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookResponseDTO> getAllBooks();
    Optional<BookResponseDTO> getBookById(Long id);
    BookResponseDTO createBook(BookRequestDTO bookRequestDTO);
    BookResponseDTO updateBook(Long id, BookRequestDTO bookRequestDTO);
    void deleteBook(Long id);
    BookResponseDTO borrowBook(Long bookId, Long borrowerId);
    BookResponseDTO returnBook(Long bookId);
    List<BookResponseDTO> searchBooksByTitle(String title);
    List<BookResponseDTO> searchBooksByAuthorName(String authorName);
}