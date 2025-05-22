package com.ravi.LibraryManagementDTO.controller;

import com.ravi.LibraryManagementDTO.dto.BorrowerDTO;
import com.ravi.LibraryManagementDTO.service.BorrowerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowers")
public class BorrowerController {

    private final BorrowerService borrowerService;

    public BorrowerController(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    @GetMapping
    public List<BorrowerDTO> getAllBorrowers() {
        return borrowerService.getAllBorrowers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowerDTO> getBorrowerById(@PathVariable Long id) {
        return borrowerService.getBorrowerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BorrowerDTO> createBorrower(@RequestBody BorrowerDTO borrowerDTO) {
        BorrowerDTO createdBorrower = borrowerService.createBorrower(borrowerDTO);
        return new ResponseEntity<>(createdBorrower, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BorrowerDTO> updateBorrower(@PathVariable Long id, @RequestBody BorrowerDTO borrowerDTO) {
        BorrowerDTO updatedBorrower = borrowerService.updateBorrower(id, borrowerDTO);
        return ResponseEntity.ok(updatedBorrower);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrower(@PathVariable Long id) {
        borrowerService.deleteBorrower(id);
        return ResponseEntity.noContent().build();
    }
}