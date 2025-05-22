package com.ravi.RestWithDBBooks;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;



@Service
public class AuthorServiceImpl implements AuthorService{
	private final AuthorRepository repository;
	
	public AuthorServiceImpl(AuthorRepository repository) {
		this.repository= repository;
	}
	
	@Override
    public List<Author> getAllAuthors() {
        return repository.findAll();
    }

    @Override
    public Optional<Author> getAuthorById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Author createAuthor(Author author) {
        return repository.save(author);
    }
    
    @Override
    public Author updateAuthor(Long id, Author author) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(author.getName());
                    existing.setBio(author.getBio());
                    return repository.save(existing);
                }).orElse(null);
    }
    
    @Override
    public void deleteAuthor(Long id) {
        repository.deleteById(id);
    }

   
}
