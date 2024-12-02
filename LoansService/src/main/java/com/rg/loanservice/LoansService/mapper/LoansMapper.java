package com.rg.loanservice.LoansService.mapper;

import com.rg.loanservice.LoansService.dto.LoanDTO;
import com.rg.loanservice.LoansService.entity.Loan;

public class LoansMapper {
    public static LoanDTO mapToLoanDto(Loan loan, LoanDTO loanDto) {
        loanDto.setLoanNumber(loan.getLoanNumber());
        loanDto.setAmountPaid(loan.getAmountPaid());
        loanDto.setMobileNumber(loan.getMobileNumber());
        loanDto.setLoanType(loan.getLoanType());
        loanDto.setOutstandingAmount(loan.getOutstandingAmount());
        loanDto.setTotalLoan(loan.getTotalLoan());
        return loanDto;
    }

    public static Loan mapToLoan(LoanDTO loanDto, Loan loan) {
        loan.setLoanNumber(loanDto.getLoanNumber());
        loan.setAmountPaid(loanDto.getAmountPaid());
        loan.setMobileNumber(loanDto.getMobileNumber());
        loan.setLoanType(loanDto.getLoanType());
        loan.setOutstandingAmount(loanDto.getOutstandingAmount());
        loan.setTotalLoan(loanDto.getTotalLoan());
        return loan;
    }
}
