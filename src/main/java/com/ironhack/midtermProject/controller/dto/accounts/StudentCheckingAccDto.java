package com.ironhack.midtermProject.controller.dto.accounts;

import com.ironhack.midtermProject.classes.Money;
import com.ironhack.midtermProject.model.AccountHolder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.math.BigDecimal;

public class StudentCheckingAccDto extends AccountDto{

    /** --------------------Properties-------------------- **/

    @NotNull
    private String secretKey;

    /** --------------------Constructor---------------------- **/

    public StudentCheckingAccDto() {
    }

    /** -----------------Getters & Setters------------------ **/

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
