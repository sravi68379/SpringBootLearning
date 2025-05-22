package com.ravi.RestWithDBBooks;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowerRepository extends JpaRepository<Borrower, Long>{
	
}
