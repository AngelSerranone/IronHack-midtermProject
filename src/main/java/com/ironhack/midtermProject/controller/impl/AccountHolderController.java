package com.ironhack.midtermProject.controller.impl;
import com.ironhack.midtermProject.controller.dto.users.AccountHolderDto;
import com.ironhack.midtermProject.controller.interfaces.IAccountHolderController;
import com.ironhack.midtermProject.model.AccountHolder;
import com.ironhack.midtermProject.service.impl.AccountHolderService;
import com.ironhack.midtermProject.service.interfaces.IAccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AccountHolderController implements IAccountHolderController {

    @Autowired
    private IAccountHolderService accountHolderService;

    /** Route to create a new Account Holder **/
    @PostMapping("/admin/create-account-holder")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder create(@RequestBody @Valid AccountHolderDto accountHolderDto){
        return accountHolderService.create(accountHolderDto);
    }

}
