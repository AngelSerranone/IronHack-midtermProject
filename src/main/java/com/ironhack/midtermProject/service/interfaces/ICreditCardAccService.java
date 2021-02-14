package com.ironhack.midtermProject.service.interfaces;

import com.ironhack.midtermProject.controller.dto.accounts.CreditCardAccDto;
import com.ironhack.midtermProject.model.CreditCardAcc;

import java.util.List;
import java.util.Optional;

public interface ICreditCardAccService {

    /** Method to create new Savings Account **/
    public CreditCardAcc create(CreditCardAccDto creditCardAccDto);

    /** Method to find all credit card accounts (you have to be and ADMIN) **/
    public List<CreditCardAcc> showAll();

    /** Method to find one credit card account by id (you have to be and ADMIN) **/
    public Optional<CreditCardAcc> find(Long id);
}
