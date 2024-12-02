package com.rg.loanservice.LoansService.dto;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;
@ConfigurationProperties(prefix = "loans")
public record LoansContactInfoDTO(String message, Map<String,String> contactDetails)  {
}
