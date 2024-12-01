package com.rg.AccountsService.service;

import com.rg.AccountsService.dto.CustomerDTO;

public interface IAccountService {
    void createAccount(CustomerDTO customerDTO);
}
