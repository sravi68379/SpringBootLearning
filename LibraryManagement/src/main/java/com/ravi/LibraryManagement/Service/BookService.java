package com.ravi.LibraryManagement.Service;

import java.util.List;
import java.util.Optional;
import com.ravi.LibraryManagement.Model.Book;

public interface BookService {
    List<Book> getAllBooks();
    Optional<Book> getBookById(Long id);
    Book createBook(Book book); 
    Book updateBook(Long id, Book bookDetails); 
    void deleteBook(Long id);

    Book borrowBook(Long bookId, Long borrowerId);
    Book returnBook(Long bookId);
    List<Book> searchBooksByTitle(String title);
    List<Book> searchBooksByAuthorName(String authorName);
}