package com.rg.AccountsService.service;

import com.rg.AccountsService.dto.CustomerDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;

public interface IAccountService {
    void createAccount(CustomerDTO customerDTO);

    CustomerDTO fetchAccountDetails(String mobileNumber);

    boolean updateAccount(CustomerDTO customerDTO);
     @Transactional
     @Modifying
    boolean deleteAccount(String mobileNumber);
}
