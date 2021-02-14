package com.ironhack.midtermProject.model;
import com.ironhack.midtermProject.classes.Money;
import com.sun.istack.NotNull;
import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;


@Entity
@PrimaryKeyJoinColumn(name="id")
public class CreditCardAcc extends Account{

    /** --------------------Properties-------------------- **/

    @Embedded
    @AttributeOverrides(value ={
            @AttributeOverride(name = "amount", column = @Column(name = "credit_limit_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "credit_limit_currency"))
    })
    private Money creditLimit;

    @Column(columnDefinition = "DECIMAL(5,4)")
    private BigDecimal interestRate;

    /** --------------------Constructors---------------------- **/

    /** Empty Constructor **/
    public CreditCardAcc() {
    }

    /** Default Constructor with primary and secondary owner **/
    public CreditCardAcc(AccountHolder primaryOwner, AccountHolder secondaryOwner, Money balance) {
        super(primaryOwner, secondaryOwner, balance);
        setDefaultCreditLimit();
        setDefaultInterestRate();
    }

    /** Default Constructor with only primary owner **/
    public CreditCardAcc(AccountHolder primaryOwner, Money balance) {
        super(primaryOwner, balance);
        setDefaultCreditLimit();
        setDefaultInterestRate();
    }

    /** Constructor with specified credit limit and interest rate, with primary and secondary owner **/
    public CreditCardAcc(AccountHolder primaryOwner, AccountHolder secondaryOwner, Money balance, Money creditLimit, BigDecimal interestRate) {
        super(primaryOwner, secondaryOwner, balance);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
    }

    /** Constructor with specified credit limit and interest rate, with only primary owner **/
    public CreditCardAcc(AccountHolder primaryOwner, Money balance, Money creditLimit, BigDecimal interestRate) {
        super(primaryOwner, balance);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
    }

    /** -----------------Getters & Setters------------------ **/

    public Money getCreditLimit() {
        return creditLimit;
    }

    public void setDefaultCreditLimit() {
        this.creditLimit = new Money(new BigDecimal("100"));
    }

    public void setCreditLimit(Money creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setDefaultInterestRate() {
        this.interestRate = new BigDecimal("0.2");
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}
