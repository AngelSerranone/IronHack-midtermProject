package com.ironhack.midtermProject.controller.impl;

import com.ironhack.midtermProject.controller.dto.accounts.CreditCardAccDto;
import com.ironhack.midtermProject.controller.interfaces.ICreditCardAccController;
import com.ironhack.midtermProject.model.CheckingAcc;
import com.ironhack.midtermProject.model.CreditCardAcc;
import com.ironhack.midtermProject.service.interfaces.ICreditCardAccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CreditCardAccController implements ICreditCardAccController {

    @Autowired
    private ICreditCardAccService creditCardAccService;

    /** Method to create new Credit Card Accounts (you have to be an ADMIN) **/
    @PostMapping("/admin/create-creditCardAcc")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCardAcc create(@RequestBody @Valid CreditCardAccDto creditCardAccDto) {
        return creditCardAccService.create(creditCardAccDto);
    }

    /** Method to find all credit card accounts (you have to be and ADMIN) **/
    @GetMapping("/admin/creditCardAcc")
    @ResponseStatus(HttpStatus.OK)
    public List<CreditCardAcc> showAll() {
        return creditCardAccService.showAll();
    }

    /** Method to find one credit card account by id (you have to be and ADMIN) **/
    @GetMapping("/admin/creditCarAcc/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<CreditCardAcc> find(@PathVariable Long id) {
        return creditCardAccService.find(id);
    }
}
