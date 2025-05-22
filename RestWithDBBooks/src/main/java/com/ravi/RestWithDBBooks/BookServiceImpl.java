package com.ravi.RestWithDBBooks;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
@Service
public class BookServiceImpl implements BookService{
private final BookRepository repository;
	
	public BookServiceImpl(BookRepository repository) {
		this.repository= repository;
	}
	
	@Override
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Book createBook(Book book) {
        return repository.save(book);
    }
    
    @Override
    public Book updateBook(Long id, Book book) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setTitle(book.getTitle());
                    existing.setIsbn(book.getIsbn());
                    existing.setPublishedDate(book.getPublishedDate());
                    existing.setAuthorIds(book.getAuthorIds());
                    return repository.save(existing);
                }).orElse(null);
    }
    
    @Override
    public void deleteBook(Long id) {
        repository.deleteById(id);
    }
}
