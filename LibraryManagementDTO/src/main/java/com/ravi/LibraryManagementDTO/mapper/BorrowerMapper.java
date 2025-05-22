package com.ravi.LibraryManagementDTO.mapper;

import com.ravi.LibraryManagementDTO.model.Borrower;
import com.ravi.LibraryManagementDTO.model.Book; 
import com.ravi.LibraryManagementDTO.dto.BorrowerDTO;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
import java.util.Collections;

@Component
public class BorrowerMapper {

    
    public BorrowerMapper() {}

    public BorrowerDTO toDto(Borrower borrower) {
        if (borrower == null) {
            return null;
        }
        BorrowerDTO borrowerDTO = new BorrowerDTO();
        borrowerDTO.setId(borrower.getId());
        borrowerDTO.setName(borrower.getName());
        borrowerDTO.setEmail(borrower.getEmail());

        if (borrower.getBorrowedBooks() != null && !borrower.getBorrowedBooks().isEmpty()) {
            borrowerDTO.setBorrowedBooks(borrower.getBorrowedBooks().stream()
                    .map(Book::getTitle) // Extract title directly
                    .collect(Collectors.toSet()));
        } else {
            borrowerDTO.setBorrowedBooks(Collections.emptySet());
        }
        return borrowerDTO;
    }

    public Borrower toEntity(BorrowerDTO borrowerDTO) {
        if (borrowerDTO == null) {
            return null;
        }
        Borrower borrowerEntity = new Borrower(); 
        borrowerEntity.setName(borrowerDTO.getName());
        borrowerEntity.setEmail(borrowerDTO.getEmail());
        return borrowerEntity;
    }
}