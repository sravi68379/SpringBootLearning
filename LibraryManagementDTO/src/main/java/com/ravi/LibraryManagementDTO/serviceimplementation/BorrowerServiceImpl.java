package com.ravi.LibraryManagementDTO.serviceimplementation;

import com.ravi.LibraryManagementDTO.model.Borrower;
import com.ravi.LibraryManagementDTO.dto.BorrowerDTO;
import com.ravi.LibraryManagementDTO.exception.ResourceNotFoundException;
import com.ravi.LibraryManagementDTO.mapper.BorrowerMapper;
import com.ravi.LibraryManagementDTO.repository.BorrowerRepository;
import com.ravi.LibraryManagementDTO.service.BorrowerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BorrowerServiceImpl implements BorrowerService {

    private final BorrowerRepository borrowerRepository;
    private final BorrowerMapper borrowerMapper;

    public BorrowerServiceImpl(BorrowerRepository borrowerRepository, BorrowerMapper borrowerMapper) {
        this.borrowerRepository = borrowerRepository;
        this.borrowerMapper = borrowerMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BorrowerDTO> getAllBorrowers() {
        return borrowerRepository.findAll().stream()
                .map(borrowerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BorrowerDTO> getBorrowerById(Long id) {
        return borrowerRepository.findById(id).map(borrowerMapper::toDto);
    }

    @Override
    @Transactional
    public BorrowerDTO createBorrower(BorrowerDTO borrowerDTO) {
        Borrower borrower = borrowerMapper.toEntity(borrowerDTO);
        borrower.setId(null); 
        return borrowerMapper.toDto(borrowerRepository.save(borrower));
    }

    @Override
    @Transactional
    public BorrowerDTO updateBorrower(Long id, BorrowerDTO borrowerDTO) {
        Borrower existingBorrower = borrowerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrower not found with id: " + id));
        existingBorrower.setName(borrowerDTO.getName());
        existingBorrower.setEmail(borrowerDTO.getEmail());
        return borrowerMapper.toDto(borrowerRepository.save(existingBorrower));
    }

    @Override
    @Transactional
    public void deleteBorrower(Long id) {
        Borrower borrower = borrowerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrower not found with id: " + id));
        
        if (!borrower.getBorrowedBooks().isEmpty()) {
            throw new IllegalArgumentException("Cannot delete borrower " + id + " as they have books currently borrowed. Please return all books first.");
        }
        borrowerRepository.deleteById(id);
    }
}