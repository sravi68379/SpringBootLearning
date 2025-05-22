package com.ravi.LibraryManagement.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.LibraryManagement.Model.Borrower;
import com.ravi.LibraryManagement.Service.BorrowerService;
import com.ravi.LibraryManagement.exception.ResourceNotFoundException;


@RestController
@RequestMapping("/api/borrowers")
public class BorrowerController {
    private final BorrowerService borrowerService; // Changed name for clarity

    public BorrowerController(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    @GetMapping
    public List<Borrower> getAllBorrowers() {
        return borrowerService.getAllBorrowers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Borrower> getBorrowerById(@PathVariable Long id) {
        Borrower borrower = borrowerService.getBorrowerById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrower not found with id: " + id));
        return ResponseEntity.ok(borrower);
    }

    @PostMapping
    public ResponseEntity<Borrower> createBorrower(@RequestBody Borrower borrower) {
        Borrower createdBorrower = borrowerService.createBorrower(borrower);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBorrower);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Borrower> updateBorrower(@PathVariable Long id, @RequestBody Borrower borrowerDetails) {
        Borrower updatedBorrower = borrowerService.updateBorrower(id, borrowerDetails);
        return ResponseEntity.ok(updatedBorrower);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrower(@PathVariable Long id) {
        borrowerService.deleteBorrower(id);
        return ResponseEntity.noContent().build();
    }
}