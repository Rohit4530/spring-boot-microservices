package com.rg.loanservice.LoansService.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {
    @NotEmpty(message = "Mobile number cannot be empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number should be of 10 digit")
    private  String mobileNumber;
    @NotEmpty(message = "Loan type cannot be empty")
    private String loanType;
    @NotEmpty(message = "Loan number cannot be empty")
    @Pattern(regexp = "(^$|[0-9]{12})",message = "Mobile number should be of 12 digit")
    private String loanNumber;
    @PositiveOrZero
    private int totalLoan;
    @PositiveOrZero
    private int amountPaid;
    @PositiveOrZero
    private int outstandingAmount;
}
