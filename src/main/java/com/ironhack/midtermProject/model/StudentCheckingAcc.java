package com.ironhack.midtermProject.model;
import com.ironhack.midtermProject.classes.Money;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class StudentCheckingAcc extends Account{

    /** --------------------Properties-------------------- **/

    private String secretKey;

    /** --------------------Constructors---------------------- **/

    /** Empty Constructor **/
    public StudentCheckingAcc() {
    }

    /** Constructor with primary and secondary owner **/
    public StudentCheckingAcc(AccountHolder primaryOwner, AccountHolder secondaryOwner, Money balance, String secretKey) {
        super(primaryOwner, secondaryOwner, balance);
        setSecretKey(secretKey);
    }

    /** Constructor with only primary owner **/
    public StudentCheckingAcc(AccountHolder primaryOwner, Money balance, String secretKey) {
        super(primaryOwner, balance);
        setSecretKey(secretKey);
    }

    /** -----------------Getters & Setters------------------ **/

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.secretKey = passwordEncoder.encode(secretKey);
    }
}
