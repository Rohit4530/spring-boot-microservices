package com.rg.AccountsService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class AccountDTO {
    private Long accountNumber;

    private String accountType;

    private  String branchAddress;
}
