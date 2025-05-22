package com.ravi.RestWithDBBooks;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
	List<Author> getAllAuthors();
	Optional<Author> getAuthorById(Long id);
	Author createAuthor(Author author);
	Author updateAuthor(Long id, Author author);
	void deleteAuthor(Long id);
	
}
