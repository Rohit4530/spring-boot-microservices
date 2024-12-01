package com.rg.AccountsService.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Account extends  BaseEntity{

    private Long customerId;
    @Id
    private Long accountNumber;

    private String accountType;

    private  String branchAddress;


}
