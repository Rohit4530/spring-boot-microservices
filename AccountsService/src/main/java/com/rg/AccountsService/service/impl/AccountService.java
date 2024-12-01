package com.rg.AccountsService.service.impl;

import com.rg.AccountsService.dto.CustomerDTO;
import com.rg.AccountsService.repositoty.AccountsRepository;
import com.rg.AccountsService.repositoty.CustomerRepository;
import com.rg.AccountsService.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {

   private  final AccountsRepository accountsRepository;
   private  final CustomerRepository customerRepository;
    @Autowired
    public AccountService(AccountsRepository accountsRepository, CustomerRepository customerRepository) {
        this.accountsRepository = accountsRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void createAccount(CustomerDTO customerDTO) {

    }
}
