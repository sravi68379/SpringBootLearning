package com.ravi.LibraryManagementDTO.repository;

import com.ravi.LibraryManagementDTO.model.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowerRepository extends JpaRepository<Borrower, Long> {
}