package com.rg.loanservice.LoansService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {
    private  String mobileNumber;
    private String loanType;
    private String loanNumber;
    private int totalLoan;
    private int amountPaid;
    private int outstandingAmount;
}
