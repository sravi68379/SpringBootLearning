package com.ravi.RestWithDBBooks;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
@Service
public class BorrowerServiceImpl implements BorrowerService {
	private final BorrowerRepository repository;

	public BorrowerServiceImpl(BorrowerRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Borrower> getAllBorrowers() {
		return repository.findAll();
	}

	@Override
	public Optional<Borrower> getBorrowerById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Borrower createBorrower(Borrower borrower) {
		return repository.save(borrower);
	}

	@Override
	public Borrower updateBorrower(Long id, Borrower borrower) {
		return repository.findById(id).map(existing -> {
			existing.setName(borrower.getName());
			existing.setEmail(borrower.getEmail());
			
			return repository.save(existing);
		}).orElse(null);
	}

	@Override
	public void deleteBorrower(Long id) {
		repository.deleteById(id);
	}
}