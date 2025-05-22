package com.ravi.LibraryManagement.ServiceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.ravi.LibraryManagement.Model.Author;
import com.ravi.LibraryManagement.Repository.AuthorRepository;
import com.ravi.LibraryManagement.Service.AuthorService;
import com.ravi.LibraryManagement.exception.ResourceNotFoundException; // Import custom exception

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository; // Changed name for clarity

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Long id, Author authorDetails) { // Changed parameter name
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));
        existingAuthor.setName(authorDetails.getName());
        existingAuthor.setBio(authorDetails.getBio());
        return authorRepository.save(existingAuthor);
    }

    @Override
    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Author not found with id: " + id);
        }
        authorRepository.deleteById(id);
    }
}