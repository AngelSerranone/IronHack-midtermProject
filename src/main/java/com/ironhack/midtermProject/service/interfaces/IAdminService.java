package com.ironhack.midtermProject.service.interfaces;

import com.ironhack.midtermProject.classes.Money;
import com.ironhack.midtermProject.controller.dto.accounts.MoneyDto;
import com.ironhack.midtermProject.controller.dto.users.AdminDto;
import com.ironhack.midtermProject.controller.dto.users.ThirdPartyDto;
import com.ironhack.midtermProject.model.Account;
import com.ironhack.midtermProject.model.Admin;
import com.ironhack.midtermProject.model.ThirdParty;

import java.math.BigDecimal;

public interface IAdminService {

    /** Method to create a new Account Holder **/
    public Admin createAdmin(AdminDto adminDto);

    /** Method to create a new Third Party **/
    public ThirdParty createThirdParty(ThirdPartyDto thirdPartyDto);


    public Account modifyBalance(Long id, MoneyDto modifiedBalance);
}
