package com.ravi.RestWithDBBooks;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/books")
public class BorrowerController {
	private final BorrowerService service;

    public BorrowerController(BorrowerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Borrower> getAllBorrowers() {
        return service.getAllBorrowers();
    }

    @GetMapping("/{id}")
    public Optional<Borrower> getBookById(@PathVariable Long id) {
        return service.getBorrowerById(id);
    }

    @PostMapping
    public Borrower createBorrower(@RequestBody Borrower borrower) {
        return service.createBorrower(borrower);
    }

    @PutMapping("/{id}")
    public Borrower updateBorrower(@PathVariable Long id,
                                 @RequestBody Borrower borrower) {
        return service.updateBorrower(id, borrower);
    }

    @DeleteMapping("/{id}")
    public void deleteBorrower(@PathVariable Long id) {
        service.deleteBorrower(id);
    }

}
