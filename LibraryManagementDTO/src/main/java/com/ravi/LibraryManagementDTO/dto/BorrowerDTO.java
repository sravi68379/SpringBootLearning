package com.ravi.LibraryManagementDTO.dto;

import java.util.Set;
import java.util.HashSet;

public class BorrowerDTO {
    private Long id;
    private String name;
    private String email;
    private Set<String> borrowedBooks = new HashSet<>(); 

    public BorrowerDTO() {}

    public BorrowerDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    
    public BorrowerDTO(Long id, String name, String email, Set<String> borrowedBooks) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.borrowedBooks = borrowedBooks;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(Set<String> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}