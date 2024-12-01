package com.rg.AccountsService.service.impl;

import com.rg.AccountsService.Constants.AccountConstant;
import com.rg.AccountsService.dto.AccountDTO;
import com.rg.AccountsService.dto.CustomerDTO;
import com.rg.AccountsService.entity.Account;
import com.rg.AccountsService.entity.Customer;
import com.rg.AccountsService.exception.CustomerAlreadyExistException;
import com.rg.AccountsService.exception.ResourceNotFoundException;
import com.rg.AccountsService.mapper.AccountMapper;
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

       Customer savedCustomer= customerRepository.save(customer);
       accountsRepository.save(createNewAccount(savedCustomer));
    }

    @Override
    public CustomerDTO fetchAccountDetails(String mobileNumber) {
        Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("customer","mobileNumber",mobileNumber));
        Account account=accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()->new ResourceNotFoundException("account","customerId",customer.getCustomerId()));
      CustomerDTO customerDTO=  CustomerMapper.mapToCustomerDTO(customer,new CustomerDTO());
      customerDTO.setAccountDTO(AccountMapper.mapToAccountDTO(account,new AccountDTO()));
        return customerDTO;
    }

    @Override
    public boolean updateAccount(CustomerDTO customerDTO) {
        boolean isUpdated = false;
        AccountDTO accountDTO = customerDTO.getAccountDTO();
        if (accountDTO != null) {
            Account account = accountsRepository.findById(accountDTO.getAccountNumber())
                    .orElseThrow(
                            () -> new ResourceNotFoundException("account", "accountNumber", accountDTO.getAccountNumber()));

        AccountMapper.mapToAccount(accountDTO, account);
        account = accountsRepository.save(account);
        Long customerId = account.getCustomerId();
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new ResourceNotFoundException("customer", "customerId", customerId));

        CustomerMapper.mapToCustomer(customerDTO, customer);
        customerRepository.save(customer);
          isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        boolean isDeleted=false;
        Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("customer","mobileNumber",mobileNumber));
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        isDeleted=true;
        return isDeleted;
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
