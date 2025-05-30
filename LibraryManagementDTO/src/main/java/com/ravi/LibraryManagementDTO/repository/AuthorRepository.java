package com.ravi.LibraryManagementDTO.repository;

import com.ravi.LibraryManagementDTO.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}