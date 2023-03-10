package com.account.controller;

import com.account.config.LoansServiceConfig;
import com.account.model.Loan;
import com.account.model.Customer;
import com.account.model.Properties;
import com.account.repository.LoanRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class LoanController {

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    LoansServiceConfig  loansServiceConfig;

    private static final Logger logger = LoggerFactory.getLogger(LoanController.class);

    @PostMapping("/myLoan")
    @Timed(value = "getLoanDetail.time", description = "Time taken to return loan Details")
    public ArrayList<Loan> getLoanDetail(@RequestHeader("ChedjouBank-correlation-id") String correlationId,@RequestBody Customer customer){

        logger.debug("ChedjouBank-correlation-id : {}. ", correlationId);
        logger.info("getLoanDetail method has started");

        ArrayList<Loan> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
        logger.info("getLoanDetail method is ending");

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
