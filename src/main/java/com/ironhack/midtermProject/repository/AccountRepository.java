package com.ironhack.midtermProject.repository;
import com.ironhack.midtermProject.model.Account;
import com.ironhack.midtermProject.model.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    /** Method to find by user name **/
    public Account findByPrimaryOwner(AccountHolder accountHolder);
}
