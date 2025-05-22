package com.ravi.LibraryManagement.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ravi.LibraryManagement.Model.Borrower;



public interface BorrowerRepository extends JpaRepository<Borrower, Long> {
	
}