package com.ironhack.midtermProject.model;
import com.ironhack.midtermProject.classes.Money;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class SavingsAcc extends Account{

    /** --------------------Properties-------------------- **/

    private String secretKey;

    @Embedded
    @AttributeOverrides(value ={
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "minimum_balance_currency"))
    })
    private Money minimumBalance;

    @Column(columnDefinition = "DECIMAL(5,4)")
    private BigDecimal interestRate;

    private Boolean belowMinimumBalance;

    /** --------------------Constructors---------------------- **/

    /** Empty Constructor **/
    public SavingsAcc() {
    }

    /** Default Constructor with primary and secondary owner **/
    public SavingsAcc(AccountHolder primaryOwner, AccountHolder secondaryOwner, Money balance, String secretKey) {
        super(primaryOwner, secondaryOwner, balance);
        setSecretKey(secretKey);
        setDefaultMinimumBalance();
        setDefaultInterestRate();
        setBelowMinimumBalance(false);
    }

    /** Default Constructor with only primary owner **/
    public SavingsAcc(AccountHolder primaryOwner, Money balance, String secretKey) {
        super(primaryOwner, balance);
        setSecretKey(secretKey);
        setDefaultMinimumBalance();
        setDefaultInterestRate();
        setBelowMinimumBalance(false);
    }

    /** Constructor with specified minimum balance and interest rate, with primary and secondary owner **/
    public SavingsAcc(AccountHolder primaryOwner, AccountHolder secondaryOwner, Money balance, String secretKey, Money minimumBalance, BigDecimal interestRate) {
        super(primaryOwner, secondaryOwner, balance);
        setSecretKey(secretKey);
        setMinimumBalance(minimumBalance);
        setInterestRate(interestRate);
        setBelowMinimumBalance(false);
    }

    /** Constructor with specified minimum balance and interest rate, with only primary owner **/
    public SavingsAcc(AccountHolder primaryOwner, Money balance, String secretKey, Money minimumBalance, BigDecimal interestRate) {
        super(primaryOwner, balance);
        setSecretKey(secretKey);
        setMinimumBalance(minimumBalance);
        setInterestRate(interestRate);
        setBelowMinimumBalance(false);
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

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setDefaultMinimumBalance() {
        this.minimumBalance = new Money(new BigDecimal("1000"));
    }

    // to set minimum balance when specified
    public void setMinimumBalance(Money minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setDefaultInterestRate() {
        this.interestRate = new BigDecimal("0.0025");
    }

    // to set interest rate when specified
    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Boolean getBelowMinimumBalance() {
        return belowMinimumBalance;
    }

    public void setBelowMinimumBalance(Boolean belowMiniminBalance) {
        this.belowMinimumBalance = belowMiniminBalance;
    }
}
