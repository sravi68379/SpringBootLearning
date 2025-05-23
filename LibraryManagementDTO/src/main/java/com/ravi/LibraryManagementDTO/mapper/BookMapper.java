package com.ravi.LibraryManagementDTO.mapper;

import com.ravi.LibraryManagementDTO.model.Book;
import com.ravi.LibraryManagementDTO.model.Author; 
import com.ravi.LibraryManagementDTO.dto.BookRequestDTO;
import com.ravi.LibraryManagementDTO.dto.BookResponseDTO;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
import java.util.Collections; // For Collections.emptySet()

@Component
public class BookMapper {

    
    public BookMapper() {}

    public BookResponseDTO toResponseDto(Book book) {
        if (book == null) {
            return null;
        }
        BookResponseDTO dto = new BookResponseDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setIsbn(book.getIsbn());
        dto.setPublishedDate(book.getPublishedDate());

        if (book.getAuthors() != null && !book.getAuthors().isEmpty()) {
            dto.setAuthorNames(book.getAuthors().stream()
                    .map(Author::getName) // Extract name directly
                    .collect(Collectors.toSet()));
        } else {
            dto.setAuthorNames(Collections.emptySet());
        }

        if (book.getBorrower() != null) {
            dto.setBorrowerName(book.getBorrower().getName()); // Extract name directly
        } else {
            dto.setBorrowerName(null); // Or an empty string, depending on preference
        }
        return dto;
    }

    public Book toEntity(BookRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }
        Book book = new Book();
        book.setTitle(requestDTO.getTitle());
        book.setIsbn(requestDTO.getIsbn());
        book.setPublishedDate(requestDTO.getPublishedDate());
        return book;
    }
}