package com.ironhack.midtermProject.controller.impl;
import com.ironhack.midtermProject.controller.dto.accounts.SavingsAccDto;
import com.ironhack.midtermProject.controller.interfaces.ISavingsAccController;
import com.ironhack.midtermProject.model.CheckingAcc;
import com.ironhack.midtermProject.model.SavingsAcc;
import com.ironhack.midtermProject.service.interfaces.ISavingsAccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class SavingsAccController implements ISavingsAccController {

    @Autowired
    private ISavingsAccService savingsAccService;


    /** Method to create new Checking Account (you have to be an ADMIN) **/
    @PostMapping("/admin/create-savingAcc")
    @ResponseStatus(HttpStatus.CREATED)
    public SavingsAcc create(@RequestBody @Valid SavingsAccDto savingsAccDto) {
        return savingsAccService.create(savingsAccDto);
    }

    /** Method to find all savings accounts (you have to be and ADMIN) **/
    @GetMapping("/admin/savingsAcc")
    @ResponseStatus(HttpStatus.OK)
    public List<SavingsAcc> showAll() {
        return savingsAccService.showAll();
    }

    /** Method to find one savings account by id (you have to be and ADMIN) **/
    @GetMapping("/admin/savings/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<SavingsAcc> find(@PathVariable Long id) {
        return savingsAccService.find(id);
    }
}
