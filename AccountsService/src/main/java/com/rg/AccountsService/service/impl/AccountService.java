package com.rg.AccountsService.service.impl;

import com.rg.AccountsService.Constants.AccountConstant;
import com.rg.AccountsService.dto.CustomerDTO;
import com.rg.AccountsService.entity.Account;
import com.rg.AccountsService.entity.Customer;
import com.rg.AccountsService.exception.CustomerAlreadyExistException;
import com.rg.AccountsService.mapper.CustomerMapper;
import com.rg.AccountsService.repositoty.AccountsRepository;
import com.rg.AccountsService.repositoty.CustomerRepository;
import com.rg.AccountsService.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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
        Customer customer= CustomerMapper.mapToCustomer(customerDTO, new Customer());
        Optional<Customer> customerOptional=customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
        if(customerOptional.isPresent()){
            throw  new CustomerAlreadyExistException("customer already registered with mobile number: "+customerDTO.getMobileNumber());
        }
       customer.setCreatedBy("Anonymous");
        customer.setCreatedAt(LocalDateTime.now());
       Customer savedCustomer= customerRepository.save(customer);
       accountsRepository.save(createNewAccount(savedCustomer));
    }
    public Account createNewAccount(Customer customer){
        Account newAccount=new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccountNumber=1000000000+ (long) Math.floor(Math.random()*900000000);
        newAccount.setAccountNumber(randomAccountNumber);
        newAccount.setAccountType(AccountConstant.SAVINGS);
        newAccount.setBranchAddress(AccountConstant.ADDRESS);
        newAccount.setCreatedBy("Anonymous");
        newAccount.setCreatedAt(LocalDateTime.now());
        return accountsRepository.save(newAccount);

    }
}
