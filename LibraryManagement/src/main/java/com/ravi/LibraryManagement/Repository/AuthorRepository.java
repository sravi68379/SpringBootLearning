package com.ravi.LibraryManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ravi.LibraryManagement.Model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
	
}