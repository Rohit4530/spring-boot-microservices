package com.rg.loanservice.LoansService.service;

import com.rg.loanservice.LoansService.constant.LoansConstant;
import com.rg.loanservice.LoansService.dto.LoanDTO;
import com.rg.loanservice.LoansService.entity.Loan;
import com.rg.loanservice.LoansService.exception.LoanAlreadyExistException;
import com.rg.loanservice.LoansService.exception.ResourceNotFoundException;
import com.rg.loanservice.LoansService.mapper.LoansMapper;
import com.rg.loanservice.LoansService.repository.LoansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class LoansService implements ILoansService{
    private  final LoansRepository loneRepository;
    @Autowired
    public LoansService(LoansRepository loneRepository) {
        this.loneRepository = loneRepository;
    }


    @Override
    public void create(String mobileNumber) {
        Optional<Loan> optionalLoan=loneRepository.findByMobileNumber(mobileNumber);
        if(optionalLoan.isPresent()){
            throw  new LoanAlreadyExistException("Loan already taken with mobile number :"+mobileNumber);
        }else{
            loneRepository.save(createNewLoan(mobileNumber));
        }
    }

    @Override
    public LoanDTO fetchLoan(String mobileNumber) {
        Loan loan=loneRepository.findByMobileNumber(mobileNumber).orElseThrow(()->new ResourceNotFoundException("Loan is not registered with mobile number: "+mobileNumber));
        return LoansMapper.mapToLoanDto(loan ,new LoanDTO());
    }

    @Override
    public boolean update(LoanDTO loanDto) {
        Loan loan=loneRepository.findByMobileNumber(loanDto.getMobileNumber()).orElseThrow(
                ()->new ResourceNotFoundException("Loan might not taken with mobile number: "+loanDto.getMobileNumber())
        );
        LoansMapper.mapToLoan(loanDto,loan);
        loneRepository.save(loan);
        return true;
    }

    @Override
    public boolean deleteByMobileNumber(String mobileNumber) {
        Loan loan=loneRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Cannot delete the record with unregistered mobile number: "+mobileNumber)
        );
        loneRepository.deleteById(loan.getLoanId());
        return true;
    }

    private Loan createNewLoan(String mobileNumber) {
        Loan loan=new Loan();
        long randomLoanNumber=100000000000L+new Random().nextInt(900000000);
        loan.setLoanNumber(Long.toString(randomLoanNumber));
        loan.setMobileNumber(mobileNumber);
        loan.setLoanType("HOME_LONE");
        loan.setTotalLoan(LoansConstant.NEW_LOAN_LIMIT);
        loan.setAmountPaid(0);
        loan.setOutstandingAmount(LoansConstant.NEW_LOAN_LIMIT);
        return loan;
    }

}
