package com.ironhack.midtermProject.service.impl;
import com.ironhack.midtermProject.classes.Address;
import com.ironhack.midtermProject.controller.dto.users.AccountHolderDto;
import com.ironhack.midtermProject.enums.SystemRole;
import com.ironhack.midtermProject.model.AccountHolder;
import com.ironhack.midtermProject.model.Role;
import com.ironhack.midtermProject.repository.AccountHolderRepository;
import com.ironhack.midtermProject.repository.RoleRepository;
import com.ironhack.midtermProject.service.interfaces.IAccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountHolderService implements IAccountHolderService {

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private RoleRepository roleRepository;

    /** Method to create a new AccountHolder and associate his role **/
    public AccountHolder create(AccountHolderDto accountHolderDto) {
        AccountHolder accountHolder = new AccountHolder(accountHolderDto.getName(),
                accountHolderDto.getUsername(),
                accountHolderDto.getPassword(),
                accountHolderDto.getDateOfBirth(),
                new Address(accountHolderDto.getPrimaryAddress()));
        // Verify if i have 1 or 2 address and call the appropriate method
        if (accountHolderDto.getMailingAddress()!=null) {
            accountHolder.setMailingAddress(new Address(accountHolderDto.getMailingAddress()));
        }
        accountHolderRepository.save(accountHolder);
        roleRepository.save(new Role(SystemRole.ACCOUNT_HOLDER, accountHolder));
        return accountHolder;
    }
}
