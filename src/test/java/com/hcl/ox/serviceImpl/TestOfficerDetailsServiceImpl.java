package com.hcl.ox.serviceImpl;

import static org.junit.Assert.assertEquals;

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
public class TestOfficerDetailsServiceImpl {

	@InjectMocks
	OfficerDetailsServiceImpl officerDetailsServiceImpl;

	@Mock
	LoanRepo loanRepo;

	@Mock
	CustomerRepo customerRepo;

	@Test
	public void testapprovingLoan() {
		long customerId = 10L;
		Loan loan = new Loan();
		loan.setLoanAccountNumber(10L);
		loan.setLoanStatus("pending");
		loan.setLoanAmount(100);
		loan.setSecurityAmount(200);
		Mockito.when(loanRepo.findByCustomer(Mockito.anyObject())).thenReturn(loan);
		Mockito.when(loanRepo.findByloanAccountNumber(Mockito.anyLong())).thenReturn(loan);
		Customer customer = new Customer();
		customer.setAge(45);
		customer.setCreditScore(890);
		Mockito.when(customerRepo.findBycustomerId(Mockito.anyLong())).thenReturn(customer);
		Mockito.when(loanRepo.save(Mockito.anyObject())).thenReturn(loan);
		String res = officerDetailsServiceImpl.approvingLoan(customerId);
		assertEquals("Aprooved loan", res);

	}

	@Test
	public void testapprovingLoan_3() {
		long customerId = 10L;
		Loan loan = new Loan();
		loan.setLoanAccountNumber(10L);
		loan.setLoanStatus("pending");
		loan.setLoanAmount(100);
		loan.setSecurityAmount(200);
		Mockito.when(loanRepo.findByCustomer(Mockito.anyObject())).thenReturn(loan);
		Mockito.when(loanRepo.findByloanAccountNumber(Mockito.anyLong())).thenReturn(loan);
		Customer customer = new Customer();
		customer.setAge(15);
		customer.setCreditScore(490);
		Mockito.when(customerRepo.findBycustomerId(Mockito.anyLong())).thenReturn(customer);
		Mockito.when(loanRepo.save(Mockito.anyObject())).thenReturn(loan);
		String res = officerDetailsServiceImpl.approvingLoan(customerId);
		assertEquals("either you credit score is not sufficient or your age is not in between 24&60 ", res);

	}

	@Test
	public void testapprovingLoan_1() {
		long customerId = 10L;
		Loan loan = null;
		Mockito.when(loanRepo.findByCustomer(Mockito.anyObject())).thenReturn(loan);
		String res = officerDetailsServiceImpl.approvingLoan(customerId);
		assertEquals("he can apply for the loan or he is not having loanaccount", res);

	}

	@Test
	public void testapprovingLoan_2() {
		long customerId = 10L;
		Loan loan = new Loan();
		loan.setLoanAccountNumber(10L);
		loan.setLoanStatus("pending");
		loan.setLoanAmount(100);
		loan.setSecurityAmount(50);
		Mockito.when(loanRepo.findByCustomer(Mockito.anyObject())).thenReturn(loan);
		Mockito.when(loanRepo.findByloanAccountNumber(Mockito.anyLong())).thenReturn(loan);
		Customer customer = new Customer();
		customer.setAge(15);
		customer.setCreditScore(490);
		Mockito.when(customerRepo.findBycustomerId(Mockito.anyLong())).thenReturn(customer);
		String res = officerDetailsServiceImpl.approvingLoan(customerId);
		assertEquals("security adderess amount is not suffiecient", res);
	}
	

	@Test
	public void testapprovingLoan_4() {
		long customerId = 10L;
		Loan loan = new Loan();
		loan.setLoanAccountNumber(10L);
		loan.setLoanStatus("rejected");
		Mockito.when(loanRepo.findByCustomer(Mockito.anyObject())).thenReturn(loan);
		Mockito.when(loanRepo.findByloanAccountNumber(Mockito.anyLong())).thenReturn(loan);
		Customer customer = new Customer();
		customer.setAge(15);
		customer.setCreditScore(490);
		String res = officerDetailsServiceImpl.approvingLoan(customerId);
		assertEquals("customer is already having loan", res);

	}
}
