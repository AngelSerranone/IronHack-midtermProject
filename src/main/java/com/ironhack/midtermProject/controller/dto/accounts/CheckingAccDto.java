package com.ironhack.midtermProject.controller.dto.accounts;
import com.ironhack.midtermProject.model.AccountHolder;
import com.sun.istack.NotNull;
import javax.persistence.*;
import java.math.BigDecimal;

public class CheckingAccDto extends AccountDto{

    /** --------------------Properties-------------------- **/

    @NotNull
    private String secretKey;

    private BigDecimal monthlyMaintenanceFee;

    private BigDecimal minimumBalance;

    /** --------------------Constructor---------------------- **/

    public CheckingAccDto() {
    }

    /** -----------------Getters & Setters------------------ **/

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public BigDecimal getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(BigDecimal monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }
}
