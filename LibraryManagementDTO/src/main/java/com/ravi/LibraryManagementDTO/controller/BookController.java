package com.ravi.LibraryManagementDTO.controller;

import com.ravi.LibraryManagementDTO.dto.BookRequestDTO;
import com.ravi.LibraryManagementDTO.dto.BookResponseDTO;
import com.ravi.LibraryManagementDTO.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookResponseDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody BookRequestDTO bookRequestDTO) {
        BookResponseDTO createdBook = bookService.createBook(bookRequestDTO);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long id, @RequestBody BookRequestDTO bookRequestDTO) {
        BookResponseDTO updatedBook = bookService.updateBook(id, bookRequestDTO);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{bookId}/borrow/{borrowerId}")
    public ResponseEntity<BookResponseDTO> borrowBook(@PathVariable Long bookId, @PathVariable Long borrowerId) {
        BookResponseDTO borrowedBook = bookService.borrowBook(bookId, borrowerId);
        return ResponseEntity.ok(borrowedBook);
    }

    @PutMapping("/{bookId}/return")
    public ResponseEntity<BookResponseDTO> returnBook(@PathVariable Long bookId) {
        BookResponseDTO returnedBook = bookService.returnBook(bookId);
        return ResponseEntity.ok(returnedBook);
    }

    @GetMapping("/search/title")
    public List<BookResponseDTO> searchBooksByTitle(@RequestParam String query) {
        return bookService.searchBooksByTitle(query);
    }

    @GetMapping("/search/author")
    public List<BookResponseDTO> searchBooksByAuthorName(@RequestParam String query) {
        return bookService.searchBooksByAuthorName(query);
    }
}