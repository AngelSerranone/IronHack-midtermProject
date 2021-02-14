package com.ironhack.midtermProject.service.impl;

import com.ironhack.midtermProject.classes.Money;
import com.ironhack.midtermProject.classes.Utils;
import com.ironhack.midtermProject.controller.dto.accounts.SavingsAccDto;
import com.ironhack.midtermProject.model.AccountHolder;
import com.ironhack.midtermProject.model.CheckingAcc;
import com.ironhack.midtermProject.model.SavingsAcc;
import com.ironhack.midtermProject.repository.AccountHolderRepository;
import com.ironhack.midtermProject.repository.SavingsAccRepository;
import com.ironhack.midtermProject.service.interfaces.ISavingsAccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class SavingsAccService implements ISavingsAccService {

    @Autowired
    private SavingsAccRepository savingsAccRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;


    /** Method to create new Savings Account **/
    public SavingsAcc create(SavingsAccDto savingsAccDto) {
        SavingsAcc savingsAcc;
        Optional<AccountHolder> accountHolder = accountHolderRepository.findById(savingsAccDto.getPrimaryOwnerId());
        if (accountHolder.isPresent()) {
            savingsAcc = new SavingsAcc(accountHolder.get(),
                    new Money(savingsAccDto.getBalance()),
                    savingsAccDto.getSecretKey());
            if (savingsAccDto.getSecondaryOwnerId() != null){
                Optional<AccountHolder> secondaryOwner = accountHolderRepository.findById(savingsAccDto.getSecondaryOwnerId());
                if (secondaryOwner.isPresent()){
                    savingsAcc.setSecondaryOwner(secondaryOwner.get());
                }
            }
            if (savingsAccDto.getMinimumBalance()!=null) {
                savingsAcc.setMinimumBalance(new Money(savingsAccDto.getMinimumBalance()));
            }
            if (savingsAccDto.getInterestRate()!=null) {
                savingsAcc.setInterestRate(savingsAccDto.getInterestRate());
            }
            return savingsAccRepository.save(savingsAcc);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Primary owner not found");
        }
    }


    /** Method to find all savings accounts (you have to be and ADMIN) **/
    public List<SavingsAcc> showAll(){
        if (savingsAccRepository.findAll().size()==0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No checking accounts found");
        }
        return savingsAccRepository.findAll();
    }

    /** Method to find one savings account by id (you have to be and ADMIN) **/
    public Optional<SavingsAcc> find(Long id){
        if (savingsAccRepository.findById(id).isPresent()){
            return savingsAccRepository.findById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no account with the provided id");
        }
    }



}
