package com.rg.AccountsService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    @NotEmpty(message = "name should not be empty")
    @Size(min = 2,max = 20,message = "name should be of 2 to 20 characters")
    private String name;
    @NotEmpty(message = "email should not be empty")
    @Email
    private  String email;
   @Pattern(regexp = "[0-9]{10}",message = "mobile number should be of 10 digits")
    private  String mobileNumber;
    private AccountDTO accountDTO;
}
