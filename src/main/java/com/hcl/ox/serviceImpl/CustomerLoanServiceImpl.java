package com.hcl.ox.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ox.entity.Customer;
import com.hcl.ox.entity.Loan;
import com.hcl.ox.entity.Officer;
import com.hcl.ox.repository.CustomerRepo;
import com.hcl.ox.repository.LoanRepo;
import com.hcl.ox.service.CustomerLoanService;

@Service
public class CustomerLoanServiceImpl implements CustomerLoanService {

	@Autowired
	LoanRepo loanRepo;

	@Autowired
	CustomerRepo customerRepo;
	/*
	@Override
	
	 * public List<Customer> loanRejections(long id) {
	 * 
	 * Officer Officer = new Officer(); Officer.setId(id); List<Loan> loan =
	 * loanRepo.findByOfficer(Officer); if (loan != null) { List<Customer> li = new
	 * ArrayList<>(); Customer customer= new Customer(); if
	 * (loan.getLoanStatus().equals("Rejected"))
	 * customer=customerRepo.findBycustomerId(loan.getCustomer().getCustomerId());
	 * li.add(customer); return li; } return null;
	 * 
	 * }
	 */

	/*
	 * public List<Customer> loanRejections(long id) {
	 * 
	 * Officer Officer = new Officer(); Officer.setId(id); List<Loan> loan =
	 * loanRepo.findByOfficer(Officer); if (loan != null) { List<Customer> li = new
	 * ArrayList<>(); Customer customer = new Customer(); for (Loan loans : loan) {
	 * if (loans.getLoanStatus().equals("Rejected")) customer =
	 * customerRepo.findBycustomerId(loans.getCustomer().getCustomerId());
	 * li.add(customer); return li; } } return null;
	 * 
	 * }
	 * 
	 * @Override public List<Customer> loanApproved(long id) { Officer Officer = new
	 * Officer(); Officer.setId(id); List<Loan> loan =
	 * loanRepo.findByOfficer(Officer); if (null != loan) { List<Customer> li = new
	 * ArrayList<>(); Customer customer = new Customer(); for (Loan loans : loan) {
	 * if (loans.getLoanStatus().equals("Approved"))
	 * 
	 * customer =
	 * customerRepo.findBycustomerId(loans.getCustomer().getCustomerId());
	 * li.add(customer); }
	 * 
	 * return li; } return null; }
	 */

		@Override
	public List<Customer> loanApprovedOrLoanRejections(long id,String status) {

		Officer Officer = new Officer();
		Officer.setId(id);
		List<Loan> loan = loanRepo.findByOfficer(Officer);
		if (loan != null) {
			List<Customer> li = new ArrayList<>();
			Customer customer = new Customer();
			for(Loan loans:loan) {
			if (loans.getLoanStatus().equals(status)) {
				customer = customerRepo.findBycustomerId(loans.getCustomer().getCustomerId());
			li.add(customer);
			}
		}
			return li;
		}
		return null;
	}

}
