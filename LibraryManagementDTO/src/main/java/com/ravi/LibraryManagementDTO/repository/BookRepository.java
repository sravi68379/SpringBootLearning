package com.ravi.LibraryManagementDTO.repository;

import com.ravi.LibraryManagementDTO.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);

    @Query("SELECT b FROM Book b JOIN b.authors a WHERE lower(a.name) LIKE lower(concat('%', :authorName, '%'))")
    List<Book> findByAuthorNameContainingIgnoreCase(@Param("authorName") String authorName);
}