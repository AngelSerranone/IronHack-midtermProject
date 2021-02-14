package com.ironhack.midtermProject.controller.interfaces;

import com.ironhack.midtermProject.controller.dto.accounts.SavingsAccDto;
import com.ironhack.midtermProject.model.CreditCardAcc;
import com.ironhack.midtermProject.model.SavingsAcc;

import java.util.List;
import java.util.Optional;

public interface ISavingsAccController {

    /** Method to create new Savings Account **/
    public SavingsAcc create(SavingsAccDto savingsAccDto);

    /** Method to find all savings accounts (you have to be and ADMIN) **/
    public List<SavingsAcc> showAll();

    /** Method to find one savings account by id (you have to be and ADMIN) **/
    public Optional<SavingsAcc> find(Long id);
}
