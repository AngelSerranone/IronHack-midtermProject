package com.ironhack.midtermProject.controller.interfaces;
import com.ironhack.midtermProject.controller.dto.users.AccountHolderDto;
import com.ironhack.midtermProject.model.AccountHolder;


public interface IAccountHolderController {

    /** Method to create a new Account Holder **/
    public AccountHolder create(AccountHolderDto accountHolderDto);
}
