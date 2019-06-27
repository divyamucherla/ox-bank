package com.hcl.ox.serviceImpl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.ox.entity.Customer;
import com.hcl.ox.entity.Loan;
import com.hcl.ox.repository.CustomerRepo;
import com.hcl.ox.repository.LoanRepo;

@RunWith(MockitoJUnitRunner.class)
public class TestCustomerLoanServiceImpl {

	@InjectMocks
	CustomerLoanServiceImpl customerLoanServiceImpl;

	@Mock
	LoanRepo loanRepo;

	@Mock
	CustomerRepo customerRepo;

	/*
	 * @Test public void testLoanRejections() { long id=123L; Loan loan = new
	 * Loan(); loan.setLoanStatus("Rejected");
	 * Mockito.when(loanRepo.findByOfficer(Mockito.anyObject())).thenReturn(loan);
	 * Customer customer= new Customer(); customer.setAge(12);
	 * customer.setCreditScore(123); customer.setCustomerId(1234);
	 * loan.setCustomer(customer);
	 * Mockito.when(customerRepo.findBycustomerId(Mockito.anyLong())).thenReturn(
	 * customer); customerLoanServiceImpl.loanRejections(id); }
	 */

	/*
	 * @Test public void testloanApproved() {
	 * 
	 * long id=123L; Loan loan = new Loan(); loan.setLoanStatus("Approved");
	 * Mockito.when(loanRepo.findByOfficer(Mockito.anyObject())).thenReturn(loan);
	 * Customer customer= new Customer(); customer.setAge(12);
	 * customer.setCreditScore(123); customer.setCustomerId(1234);
	 * loan.setCustomer(customer);
	 * Mockito.when(customerRepo.findBycustomerId(Mockito.anyLong())).thenReturn(
	 * customer); customerLoanServiceImpl.loanApproved(id);
	 * 
	 * }
	 */

	@Test
	public void testloanApprovedOrLoanRejections() {
		long id = 123L;
		List<Loan> loanList = new ArrayList<Loan>();
		Loan loan = new Loan();
		loan.setLoanStatus("Rejected");
		loanList.add(loan);
		Mockito.when(loanRepo.findByOfficer(Mockito.anyObject())).thenReturn(loanList);
		Customer customer = new Customer();
		customer.setAge(12);
		customer.setCreditScore(123);
		customer.setCustomerId(1234);
		loan.setCustomer(customer);
		Mockito.when(customerRepo.findBycustomerId(Mockito.anyLong())).thenReturn(customer);
		customerLoanServiceImpl.loanApprovedOrLoanRejections(id, loan.getLoanStatus());
		assertEquals("Rejected", loan.getLoanStatus());

	}

}
