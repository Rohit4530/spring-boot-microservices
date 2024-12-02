package com.rg.loanservice.LoansService.controller;

import com.rg.loanservice.LoansService.constant.LoansConstant;
import com.rg.loanservice.LoansService.dto.LoanDTO;
import com.rg.loanservice.LoansService.dto.LoansContactInfoDTO;
import com.rg.loanservice.LoansService.dto.ResponseDTO;
import com.rg.loanservice.LoansService.service.ILoansService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoansController {

    private final ILoansService iloanService;

    private  final LoansContactInfoDTO loansContactInfoDto;

    @Autowired
    public LoansController(ILoansService iloanService, LoansContactInfoDTO loansContactInfoDto) {
        this.iloanService = iloanService;
        this.loansContactInfoDto = loansContactInfoDto;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO>createLoans(@RequestParam
                                                  @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number should be of 10 digit")
                                                  String mobileNumber){
        iloanService.create(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(
                        LoansConstant.STATUS_201,
                        LoansConstant.MESSAGE_201
                ));
    }
    @GetMapping("/fetch")
    public ResponseEntity<LoanDTO> fetchLoan(
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number should be of 10 digit")
            String mobileNumber
    ){
        LoanDTO loanDto=iloanService.fetchLoan(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loanDto);
    }
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO>updateLoanDetails(@Valid @RequestBody LoanDTO loanDto){
        boolean isUpdated=iloanService.update(loanDto);
        if(isUpdated){
            return  ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(
                            LoansConstant.STATUS_200,
                            LoansConstant.MESSAGE_200
                    ));
        }else{
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(
                            LoansConstant.STATUS_400,
                            LoansConstant.MESSAGE_400
                    ));
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO>deleteLoanDetails(
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number should be of 10 digit")
            String mobileNumber){
        boolean isDeleted=iloanService.deleteByMobileNumber(mobileNumber);
        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(
                            LoansConstant.STATUS_200,
                            LoansConstant.MESSAGE_200
                    ));
        }else{
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(
                            LoansConstant.STATUS_400,
                            LoansConstant.MESSAGE_400
                    ));
        }
    }
    @GetMapping("/contact-info")
    public ResponseEntity<LoansContactInfoDTO>getContactDetails(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loansContactInfoDto);
    }

}
