package com.ravi.LibraryManagementDTO.serviceimplementation;

import com.ravi.LibraryManagementDTO.model.Author;
import com.ravi.LibraryManagementDTO.dto.AuthorDTO;
import com.ravi.LibraryManagementDTO.exception.ResourceNotFoundException;
import com.ravi.LibraryManagementDTO.mapper.AuthorMapper;
import com.ravi.LibraryManagementDTO.repository.AuthorRepository;
import com.ravi.LibraryManagementDTO.service.AuthorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(authorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AuthorDTO> getAuthorById(Long id) {
        return authorRepository.findById(id).map(authorMapper::toDto);
    }

    @Override
    @Transactional
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = authorMapper.toEntity(authorDTO);
        author.setId(null);
        return authorMapper.toDto(authorRepository.save(author));
    }

    @Override
    @Transactional
    public AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));
        existingAuthor.setName(authorDTO.getName());
        existingAuthor.setBio(authorDTO.getBio());
        return authorMapper.toDto(authorRepository.save(existingAuthor));
    }

    @Override
    @Transactional
    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Author not found with id: " + id);
        }
        
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found with id " + id));
        if (!author.getBooks().isEmpty()) {
            throw new IllegalArgumentException("Cannot delete author with id " + id + " as they are associated with books. Please disassociate them first.");
        }
        authorRepository.deleteById(id);
    }
}