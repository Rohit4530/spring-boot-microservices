package com.rg.AccountsService.mapper;

import com.rg.AccountsService.dto.CustomerDTO;
import com.rg.AccountsService.entity.Customer;

public class CustomerMapper {

    public static CustomerDTO mapToCustomerDTO(Customer customer, CustomerDTO customerDTO){
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setName(customer.getName());
        customerDTO.setMobileNumber(customer.getMobileNumber());
        return customerDTO;
    }
    public static Customer mapToCustomer(CustomerDTO customerDTO,Customer customer){
        customer.setEmail(customerDTO.getEmail());
        customer.setName(customerDTO.getName());
        customer.setMobileNumber(customerDTO.getMobileNumber());
        return customer;
    }
}
