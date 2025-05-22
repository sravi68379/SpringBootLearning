package com.ravi.LibraryManagement.ServiceImplementation;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Important for managing relationships

import com.ravi.LibraryManagement.Model.Author;
import com.ravi.LibraryManagement.Model.Book;
import com.ravi.LibraryManagement.Model.Borrower;
import com.ravi.LibraryManagement.Repository.AuthorRepository;
import com.ravi.LibraryManagement.Repository.BookRepository;
import com.ravi.LibraryManagement.Repository.BorrowerRepository;
import com.ravi.LibraryManagement.Service.BookService;
import com.ravi.LibraryManagement.exception.ResourceNotFoundException;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BorrowerRepository borrowerRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, BorrowerRepository borrowerRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.borrowerRepository = borrowerRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    @Transactional
    public Book createBook(Book book) {
        if (book.getAuthorIdsInput() != null && !book.getAuthorIdsInput().isEmpty()) {
            Set<Author> authors = book.getAuthorIdsInput().stream()
                    .map(authorId -> authorRepository.findById(authorId)
                            .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + authorId)))
                    .collect(Collectors.toSet());
            book.setAuthors(authors);
        }
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book updateBook(Long id, Book bookDetails) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        existingBook.setTitle(bookDetails.getTitle());
        existingBook.setIsbn(bookDetails.getIsbn());
        existingBook.setPublishedDate(bookDetails.getPublishedDate());

        
        if (bookDetails.getAuthorIdsInput() != null) {
            if (bookDetails.getAuthorIdsInput().isEmpty()) {

                existingBook.getAuthors().clear();
            } else {
                Set<Author> authors = bookDetails.getAuthorIdsInput().stream()
                        .map(authorId -> authorRepository.findById(authorId)
                                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + authorId)))
                        .collect(Collectors.toSet());
                existingBook.setAuthors(authors);
            }
        }

        return bookRepository.save(existingBook);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        book.getAuthors().clear(); 
        if (book.getBorrower() != null) {
            book.getBorrower().getBorrowedBooks().remove(book); 
            book.setBorrower(null);
        }
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Book borrowBook(Long bookId, Long borrowerId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));

        if (book.getBorrower() != null) {
            throw new IllegalStateException("Book with id " + bookId + " is already borrowed by borrower id " + book.getBorrower().getId());
        }

        Borrower borrower = borrowerRepository.findById(borrowerId)
                .orElseThrow(() -> new ResourceNotFoundException("Borrower not found with id: " + borrowerId));

        book.setBorrower(borrower);
        borrower.getBorrowedBooks().add(book); 
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book returnBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));

        if (book.getBorrower() == null) {
            throw new IllegalStateException("Book with id " + bookId + " is not currently borrowed.");
        }
        
        Borrower borrower = book.getBorrower();
        borrower.getBorrowedBooks().remove(book); // Maintain bidirectional consistency
        book.setBorrower(null);

        return bookRepository.save(book);
    }

    @Override
    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Book> searchBooksByAuthorName(String authorName) {
        return bookRepository.findByAuthorNameContainingIgnoreCase(authorName);
    }
}