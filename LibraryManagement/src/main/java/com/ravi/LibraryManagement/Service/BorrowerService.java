package com.ravi.LibraryManagement.Service;

import java.util.List;
import java.util.Optional;
import com.ravi.LibraryManagement.Model.Borrower;

public interface BorrowerService {
    List<Borrower> getAllBorrowers();
    Optional<Borrower> getBorrowerById(Long id);
    Borrower createBorrower(Borrower borrower);
    Borrower updateBorrower(Long id, Borrower borrower);
    void deleteBorrower(Long id);
}