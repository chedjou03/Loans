package com.account.repository;

import com.account.model.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {

    ArrayList<Loan> findByCustomerIdOrderByStartDtDesc(int customerId);

}
