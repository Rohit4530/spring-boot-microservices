package com.rg.AccountsService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class CustomerDTO {
    private String name;
    private  String email;
    private  String mobileNumber;
}
