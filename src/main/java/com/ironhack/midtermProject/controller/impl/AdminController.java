package com.ironhack.midtermProject.controller.impl;
import com.ironhack.midtermProject.classes.Money;
import com.ironhack.midtermProject.controller.dto.accounts.MoneyDto;
import com.ironhack.midtermProject.controller.dto.users.AdminDto;
import com.ironhack.midtermProject.controller.dto.users.ThirdPartyDto;
import com.ironhack.midtermProject.controller.interfaces.IAdminController;
import com.ironhack.midtermProject.model.Account;
import com.ironhack.midtermProject.model.Admin;
import com.ironhack.midtermProject.model.ThirdParty;
import com.ironhack.midtermProject.service.interfaces.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
public class AdminController implements IAdminController {

    @Autowired
    private IAdminService adminService;

    /** Route to create a new Admin **/
    @PostMapping("/admin/create-new-admin")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin createAdmin(@RequestBody @Valid AdminDto adminDto) {
        return adminService.createAdmin(adminDto);
    }

    /** Route to create a new Third Party **/
    @PostMapping("/admin/create-third-party")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty createThirdParty(@RequestBody @Valid ThirdPartyDto thirdPartyDto) {
        return adminService.createThirdParty(thirdPartyDto);
    }

    /** Route to modify any account balance (you have to be an ADMIN) **/
    @PatchMapping("/admin/modify-balance/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account modifyBalance(@PathVariable("id") Long id, @RequestBody @Valid MoneyDto modifiedBalance){
        return adminService.modifyBalance(id, modifiedBalance);
    }

}
