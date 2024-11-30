package com.rg.AccountsService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountsController {

   @GetMapping("/message")
    public  String getMessage() {
        return "Good Morning";
    }

}
