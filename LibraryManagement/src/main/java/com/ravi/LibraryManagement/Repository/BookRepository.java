package com.ravi.LibraryManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ravi.LibraryManagement.Model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCase(String title);

    // Search by author's name
    @Query("SELECT b FROM Book b JOIN b.authors a WHERE lower(a.name) LIKE lower(concat('%', :authorName, '%'))")
    List<Book> findByAuthorNameContainingIgnoreCase(@Param("authorName") String authorName);

    Optional<Book> findByIdAndBorrowerIsNull(Long bookId);
    Optional<Book> findByIdAndBorrowerIsNotNull(Long bookId);
}