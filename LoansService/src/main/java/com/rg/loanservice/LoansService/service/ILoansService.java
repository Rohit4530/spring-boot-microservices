package com.rg.loanservice.LoansService.service;

import com.rg.loanservice.LoansService.dto.LoanDTO;

public interface ILoansService {
    void create(String mobileNumber);

    LoanDTO fetchLoan(String mobileNumber);

    boolean update(LoanDTO loanDto);

    boolean deleteByMobileNumber(String mobileNumber);
}
