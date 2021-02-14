package com.ironhack.midtermProject.controller.interfaces;

import com.ironhack.midtermProject.controller.dto.accounts.CheckingAccDto;
import com.ironhack.midtermProject.model.Account;
import com.ironhack.midtermProject.model.CheckingAcc;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface ICheckingAccController {

    /** Method to create new Checking Account **/
    public Account create(CheckingAccDto checkingAccDto);

    /** Method to find all checking accounts (you have to be and ADMIN) **/
    public List<CheckingAcc> showAll();

    /** Method to find one checking account by id (you have to be and ADMIN) **/
    public Optional<CheckingAcc> find(Long id);
}
