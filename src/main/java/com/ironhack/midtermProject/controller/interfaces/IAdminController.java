package com.ironhack.midtermProject.controller.interfaces;
import com.ironhack.midtermProject.classes.Money;
import com.ironhack.midtermProject.controller.dto.accounts.MoneyDto;
import com.ironhack.midtermProject.controller.dto.users.AdminDto;
import com.ironhack.midtermProject.controller.dto.users.ThirdPartyDto;
import com.ironhack.midtermProject.model.Account;
import com.ironhack.midtermProject.model.Admin;
import com.ironhack.midtermProject.model.ThirdParty;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

public interface IAdminController {

    /** Method to create a new Admin **/
    public Admin createAdmin(AdminDto adminDto);

    /** Method to create a new Third Party **/
    public ThirdParty createThirdParty(ThirdPartyDto thirdPartyDto);

    /** Route to modify any account balance (you have to be an ADMIN) **/
    public Account modifyBalance(Long id, MoneyDto modifiedBalance);

}
