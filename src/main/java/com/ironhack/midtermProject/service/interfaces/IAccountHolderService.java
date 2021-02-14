package com.ironhack.midtermProject.service.interfaces;

import com.ironhack.midtermProject.controller.dto.users.AccountHolderDto;
import com.ironhack.midtermProject.model.AccountHolder;

public interface IAccountHolderService {

    /** Method to create a new Account Holder **/
    public AccountHolder create(AccountHolderDto accountHolderDto);
}
