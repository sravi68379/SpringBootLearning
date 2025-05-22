package com.ravi.RestWithDBBooks;

import java.util.List;
import java.util.Optional;

public interface BorrowerService {
	List<Borrower> getAllBorrowers();
	Optional<Borrower> getBorrowerById(Long id);
	Borrower createBorrower(Borrower borrower);
	Borrower updateBorrower(Long id, Borrower borrower);
	void deleteBorrower(Long id);
}
