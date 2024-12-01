package com.rg.AccountsService.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    @Pattern(regexp = "[0-9]{10}",message = "account number should be of 10 digits")
    private Long accountNumber;
    @NotEmpty
    private String accountType;
    @NotEmpty
    private  String branchAddress;
}
