package com.rg.AccountsService.controller;

import com.rg.AccountsService.Constants.AccountConstant;
import com.rg.AccountsService.dto.CustomerDTO;
import com.rg.AccountsService.dto.ResponseDTO;
import com.rg.AccountsService.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api",produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountsController {
    @Autowired
    private IAccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(@RequestBody CustomerDTO customerDTO) {
        accountService.createAccount(customerDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body( new ResponseDTO(
                        AccountConstant.STATUS_201,
                        AccountConstant.MESSAGE_201
                ));
    }
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDTO>fetchAccountDetails(@RequestParam("mobileNumber") String mobileNumber) throws Exception {
       CustomerDTO customerDTO= accountService.fetchAccountDetails(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateAccount(@RequestBody CustomerDTO customerDTO) throws Exception {
        boolean isUpdated = accountService.updateAccount(customerDTO);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(
                            AccountConstant.STATUS_200,
                            AccountConstant.MESSAGE_200
                    ));
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(
                            AccountConstant.STATUS_404,
                            AccountConstant.MESSAGE_404
                    ));
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteAccount(@RequestParam("mobileNumber") String mobileNumber) throws Exception {
        boolean isDeleted = accountService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(
                            AccountConstant.STATUS_200,
                            AccountConstant.MESSAGE_200
                    ));
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(
                            AccountConstant.STATUS_404,
                            AccountConstant.MESSAGE_404
                    ));
        }
    }

}
