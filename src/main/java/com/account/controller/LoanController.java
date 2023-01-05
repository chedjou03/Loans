package com.account.controller;

import com.account.config.LoansServiceConfig;
import com.account.model.Loan;
import com.account.model.Customer;
import com.account.model.Properties;
import com.account.repository.LoanRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class LoanController {

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    LoansServiceConfig  loansServiceConfig;

    @PostMapping("/myLoan")
    public ArrayList<Loan> getLoanDetail(@RequestBody Customer customer){
        ArrayList<Loan> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
        if(loans!=null){
            return loans;
        }else {
            return null;
        }
    }

    @GetMapping("/loans/properties")
    public String getAccountsProperties() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Properties properties = new Properties(loansServiceConfig.getMsg(),
                loansServiceConfig.getBuildVersion(),
                loansServiceConfig.getActiveBranches(),
                loansServiceConfig.getMailDetails());
        String jsonStr = objectMapper.writeValueAsString(properties);
        return jsonStr;
    }
}
