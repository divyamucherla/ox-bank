package com.hcl.ox.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ox.entity.Customer;
import com.hcl.ox.entity.Loan;
import com.hcl.ox.repository.CustomerRepo;
import com.hcl.ox.repository.LoanRepo;
import com.hcl.ox.service.OfficerDetailsService;

@Service
public class OfficerDetailsServiceImpl implements OfficerDetailsService {

	@Autowired
	LoanRepo loanRepo;

	@Autowired
	CustomerRepo customerRepo;

	@Override
	public String approvingLoan(long customerId) {
		Loan loan = new Loan();
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		loan = loanRepo.findByCustomer(customer);
		if (loan != null) {
			loan = loanRepo.findByloanAccountNumber(loan.getLoanAccountNumber());
			if (loan != null &&loan.getLoanStatus().equalsIgnoreCase("pending")) {
				customer = customerRepo.findBycustomerId(customerId);
				if (loan.getSecurityAmount() >= 2 * (loan.getLoanAmount())) {
					if (customer.getCreditScore() >= 600 && (customer.getAge() >= 24 || customer.getAge() <= 60)) {
						loan.setLoanStatus("Aprooved");
						loanRepo.save(loan);
						return "Aprooved loan";
					}
					else if(customer.getCreditScore() <= 600 && (customer.getAge() <= 24 || customer.getAge() >= 60)) {
						return "either you credit score is not sufficient or your age is not in between 24&60 ";
					}
					
				} else {
					return "security adderess amount is not suffiecient";
				}

			} else if (loan != null && (loan.getLoanStatus().equalsIgnoreCase("rejected") || loan.getLoanStatus().equalsIgnoreCase("Approved"))) {

				return "customer is already having loan";
			}
		} else if (loan == null) {
			return "he can apply for the loan or he is not having loanaccount";
		}

		return null;
	}

}
