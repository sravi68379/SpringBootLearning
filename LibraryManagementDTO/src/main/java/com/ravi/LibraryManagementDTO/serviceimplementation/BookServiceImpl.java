package com.ravi.LibraryManagementDTO.serviceimplementation;

import com.ravi.LibraryManagementDTO.model.Author;
import com.ravi.LibraryManagementDTO.model.Book;
import com.ravi.LibraryManagementDTO.model.Borrower;
import com.ravi.LibraryManagementDTO.dto.BookRequestDTO;
import com.ravi.LibraryManagementDTO.dto.BookResponseDTO;
import com.ravi.LibraryManagementDTO.exception.ResourceNotFoundException;
import com.ravi.LibraryManagementDTO.mapper.BookMapper;
import com.ravi.LibraryManagementDTO.repository.AuthorRepository;
import com.ravi.LibraryManagementDTO.repository.BookRepository;
import com.ravi.LibraryManagementDTO.repository.BorrowerRepository;
import com.ravi.LibraryManagementDTO.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BorrowerRepository borrowerRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository,
                           BorrowerRepository borrowerRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.borrowerRepository = borrowerRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookResponseDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BookResponseDTO> getBookById(Long id) {
        return bookRepository.findById(id).map(bookMapper::toResponseDto);
    }

    @Override
    @Transactional
    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO) {
        Book book = bookMapper.toEntity(bookRequestDTO);
        if (bookRequestDTO.getAuthorIds() != null && !bookRequestDTO.getAuthorIds().isEmpty()) {
            Set<Author> authors = new HashSet<>(authorRepository.findAllById(bookRequestDTO.getAuthorIds()));
            if (authors.size() != bookRequestDTO.getAuthorIds().size()) {
                throw new ResourceNotFoundException("One or more authors not found for the provided IDs.");
            }
            book.setAuthors(authors);
        }
        return bookMapper.toResponseDto(bookRepository.save(book));
    }

    @Override
    @Transactional
    public BookResponseDTO updateBook(Long id, BookRequestDTO bookRequestDTO) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        existingBook.setTitle(bookRequestDTO.getTitle());
        existingBook.setIsbn(bookRequestDTO.getIsbn());
        existingBook.setPublishedDate(bookRequestDTO.getPublishedDate());

        if (bookRequestDTO.getAuthorIds() != null) {
            Set<Author> authors = new HashSet<>();
            if (!bookRequestDTO.getAuthorIds().isEmpty()) {
                 authors.addAll(authorRepository.findAllById(bookRequestDTO.getAuthorIds()));
                 if (authors.size() != bookRequestDTO.getAuthorIds().size()) {
                    throw new ResourceNotFoundException("One or more authors not found for updating the book.");
                }
            }
            existingBook.setAuthors(authors); 
        }
        return bookMapper.toResponseDto(bookRepository.save(existingBook));
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public BookResponseDTO borrowBook(Long bookId, Long borrowerId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));
        Borrower borrower = borrowerRepository.findById(borrowerId)
                .orElseThrow(() -> new ResourceNotFoundException("Borrower not found with id: " + borrowerId));

        if (book.getBorrower() != null) {
            throw new IllegalArgumentException("Book with id " + bookId + " is already borrowed by borrower id: " + book.getBorrower().getId());
        }

        book.setBorrower(borrower);
        return bookMapper.toResponseDto(bookRepository.save(book));
    }

    @Override
    @Transactional
    public BookResponseDTO returnBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));

        if (book.getBorrower() == null) {
            throw new IllegalArgumentException("Book with id " + bookId + " is not currently borrowed.");
        }

        book.setBorrower(null);
        return bookMapper.toResponseDto(bookRepository.save(book));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<BookResponseDTO> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(bookMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookResponseDTO> searchBooksByAuthorName(String authorName) {
        return bookRepository.findByAuthorNameContainingIgnoreCase(authorName).stream()
                .map(bookMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}