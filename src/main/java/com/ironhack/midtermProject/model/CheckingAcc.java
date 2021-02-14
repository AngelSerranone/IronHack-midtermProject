package com.ironhack.midtermProject.model;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.ironhack.midtermProject.classes.Money;
import com.ironhack.midtermProject.enums.AccountStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class CheckingAcc extends Account{

    /** --------------------Properties-------------------- **/

    private String secretKey;

    @Embedded
    @AttributeOverrides(value ={
            @AttributeOverride(name = "amount", column = @Column(name = "monthly_maintenance_fee_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "monthly_maintenance_fee_currency"))
    })
    private Money monthlyMaintenanceFee;

    @Embedded
    @AttributeOverrides(value ={
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "minimum_balance_currency"))
    })
    private Money minimumBalance;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate lasMonthlyMaintenance;

    private Boolean belowMinimumBalance;

    /** --------------------Constructor---------------------- **/

    /** Empty Constructor **/
    public CheckingAcc() {
    }

    /** Constructor with primary and secondary owner **/
    public CheckingAcc(AccountHolder primaryOwner, AccountHolder secondaryOwner, Money balance, String secretKey) {
        super(primaryOwner, secondaryOwner, balance);
        setSecretKey(secretKey);
        setDefaultMonthlyMaintenanceFee();
        setDefaultMinimumBalance();
        setBelowMinimumBalance(false);
        setLasMonthlyMaintenance(LocalDate.now());
    }

    /** Constructor with only primary owner **/
    public CheckingAcc(AccountHolder primaryOwner, Money balance, String secretKey) {
        super(primaryOwner, balance);
        setSecretKey(secretKey);
        setDefaultMonthlyMaintenanceFee();
        setDefaultMinimumBalance();
        setBelowMinimumBalance(false);
        setLasMonthlyMaintenance(LocalDate.now());
    }

    /** -----------------Getters & Setters------------------ **/

    public String getSecretKey() {
        return secretKey;
    }

    // To encode the secret key
    public void setSecretKey(String secretKey) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.secretKey = passwordEncoder.encode(secretKey);
    }

    public Money getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setDefaultMonthlyMaintenanceFee() {
        this.monthlyMaintenanceFee = new Money(new BigDecimal("12"));
    }

    public void setMonthlyMaintenanceFee(Money monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }


    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setDefaultMinimumBalance() {
        this.minimumBalance = new Money(new BigDecimal("250"));
    }

    public void setMinimumBalance(Money minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    @Override
    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    @Override
    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Boolean getBelowMinimumBalance() {
        return belowMinimumBalance;
    }

    public void setBelowMinimumBalance(Boolean belowMinimumBalance) {
        this.belowMinimumBalance = belowMinimumBalance;
    }

    public LocalDate getLasMonthlyMaintenance() {
        return lasMonthlyMaintenance;
    }

    public void setLasMonthlyMaintenance(LocalDate lasMonthlyMaintenance) {
        this.lasMonthlyMaintenance = lasMonthlyMaintenance;
    }
}

