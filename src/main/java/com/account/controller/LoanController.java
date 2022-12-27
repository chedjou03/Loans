package com.account.controller;

import com.account.model.Loan;
import com.account.model.Customer;
import com.account.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class LoanController {

    @Autowired
    LoanRepository loanRepository;

    @PostMapping("/myLoan")
    public ArrayList<Loan> getLoanDetail(@RequestBody Customer customer){
        ArrayList<Loan> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
        if(loans!=null){
            return loans;
        }else {
            return null;
        }

    }
}
