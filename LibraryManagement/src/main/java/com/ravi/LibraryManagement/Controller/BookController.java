package com.ravi.LibraryManagement.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RestController;

import com.ravi.LibraryManagement.Model.Book;
import com.ravi.LibraryManagement.Service.BookService;
import com.ravi.LibraryManagement.exception.ResourceNotFoundException;


@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService; 

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Book updatedBook = bookService.updateBook(id, bookDetails);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

   
    @PutMapping("/{bookId}/borrow/{borrowerId}")
    public ResponseEntity<Book> borrowBook(@PathVariable Long bookId, @PathVariable Long borrowerId) {
        Book borrowedBook = bookService.borrowBook(bookId, borrowerId);
        return ResponseEntity.ok(borrowedBook);
    }

    
    @PutMapping("/{bookId}/return")
    public ResponseEntity<Book> returnBook(@PathVariable Long bookId) {
        Book returnedBook = bookService.returnBook(bookId);
        return ResponseEntity.ok(returnedBook);
    }

    
    @GetMapping("/search/title")
    public ResponseEntity<List<Book>> searchBooksByTitle(@RequestParam("query") String title) {
        List<Book> books = bookService.searchBooksByTitle(title);
        return ResponseEntity.ok(books);
    }

    
    @GetMapping("/search/author")
    public ResponseEntity<List<Book>> searchBooksByAuthorName(@RequestParam("name") String authorName) {
        List<Book> books = bookService.searchBooksByAuthorName(authorName);
        return ResponseEntity.ok(books);
    }
}