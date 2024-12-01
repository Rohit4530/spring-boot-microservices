package com.rg.AccountsService.controller;

import com.rg.AccountsService.Constants.AccountConstant;
import com.rg.AccountsService.dto.CustomerDTO;
import com.rg.AccountsService.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api",produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountsController {


    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body( new ResponseDTO(
                        AccountConstant.STATUS_201,
                        AccountConstant.MESSAGE_201
                ));

    }

}
