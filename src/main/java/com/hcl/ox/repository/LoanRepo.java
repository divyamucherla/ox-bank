package com.hcl.ox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.ox.entity.Customer;
import com.hcl.ox.entity.Loan;
import com.hcl.ox.entity.Officer;

@Repository
public interface LoanRepo extends JpaRepository<Loan, Long> {
	
	public Loan findByCustomer(Customer customer);
	//public Loan findByloanAccountNumber();
	public Loan findByloanAccountNumber(long loanAccountNumber);
	
	public List<Loan> findByOfficer(Officer Officer);

}
