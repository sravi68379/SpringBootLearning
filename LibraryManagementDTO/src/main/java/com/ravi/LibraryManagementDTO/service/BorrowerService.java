package com.ravi.LibraryManagementDTO.service;

import com.ravi.LibraryManagementDTO.dto.BorrowerDTO;
import java.util.List;
import java.util.Optional;

public interface BorrowerService {
    List<BorrowerDTO> getAllBorrowers();
    Optional<BorrowerDTO> getBorrowerById(Long id);
    BorrowerDTO createBorrower(BorrowerDTO borrowerDTO);
    BorrowerDTO updateBorrower(Long id, BorrowerDTO borrowerDTO);
    void deleteBorrower(Long id);
}