package com.ravi.LibraryManagement.ServiceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.ravi.LibraryManagement.Model.Borrower;
import com.ravi.LibraryManagement.Repository.BorrowerRepository;
import com.ravi.LibraryManagement.Service.BorrowerService;
import com.ravi.LibraryManagement.exception.ResourceNotFoundException; // Import custom exception
import org.springframework.transaction.annotation.Transactional;


@Service
public class BorrowerServiceImpl implements BorrowerService {
    private final BorrowerRepository borrowerRepository; // Changed name for clarity

    public BorrowerServiceImpl(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }

    @Override
    public List<Borrower> getAllBorrowers() {
        return borrowerRepository.findAll();
    }

    @Override
    public Optional<Borrower> getBorrowerById(Long id) {
        return borrowerRepository.findById(id);
    }

    @Override
    public Borrower createBorrower(Borrower borrower) {
        return borrowerRepository.save(borrower);
    }

    @Override
    public Borrower updateBorrower(Long id, Borrower borrowerDetails) { // Changed parameter name
        Borrower existingBorrower = borrowerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrower not found with id: " + id));
        existingBorrower.setName(borrowerDetails.getName());
        existingBorrower.setEmail(borrowerDetails.getEmail());
        return borrowerRepository.save(existingBorrower);
    }

    @Override
    @Transactional
    public void deleteBorrower(Long id) {
        Borrower borrower = borrowerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrower not found with id: " + id));
     
        if (!borrower.getBorrowedBooks().isEmpty()) {
            borrower.getBorrowedBooks().forEach(book -> book.setBorrower(null));

        borrowerRepository.deleteById(id);
    }
}
}