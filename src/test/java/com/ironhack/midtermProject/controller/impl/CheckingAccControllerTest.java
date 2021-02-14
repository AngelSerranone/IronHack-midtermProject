package com.ironhack.midtermProject.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.midtermProject.classes.Address;
import com.ironhack.midtermProject.classes.Money;
import com.ironhack.midtermProject.controller.dto.accounts.CheckingAccDto;
import com.ironhack.midtermProject.enums.SystemRole;
import com.ironhack.midtermProject.model.*;
import com.ironhack.midtermProject.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CheckingAccControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ThirdPartyRepository thirdPartyRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CheckingAccRepository checkingAccRepository;
    @Autowired
    private SavingsAccRepository savingsAccRepository;
    @Autowired
    private StudentCheckingAccRepository studentCheckingAccRepository;
    @Autowired
    private CreditCardAccRepository creditCardAccRepository;
    @Autowired
    private UserRepository userRepository;


    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();

        Address address1=new Address("Calle del olvido, 45. Madrid");
        Address address2=new Address("Calle del perdon, 27. Cuenca");
        Address address3=new Address("Calle del martirio, 23. Albacete");

        AccountHolder owner1= new AccountHolder("Fernando Rado", "rader", "radero", LocalDate.of(2006, 10, 5), address3);
        AccountHolder owner2= new AccountHolder("Pepe Botijo", "elbotijero", "botijo", LocalDate.of(1960, 10, 5), address1, address2);

        accountHolderRepository.saveAll(List.of(owner1, owner2));

        roleRepository.save(new Role(SystemRole.ACCOUNT_HOLDER, owner1));
        roleRepository.save(new Role(SystemRole.ACCOUNT_HOLDER, owner2));

        Admin admin1= new Admin("Emilio Botín", "botin", "santander");
        adminRepository.save(admin1);
        roleRepository.save(new Role(SystemRole.ADMIN, admin1));

    }

    @AfterEach
    void tearDown() {
        checkingAccRepository.deleteAll();
        studentCheckingAccRepository.deleteAll();
        savingsAccRepository.deleteAll();
        creditCardAccRepository.deleteAll();
        accountRepository.deleteAll();
        accountHolderRepository.deleteAll();
        thirdPartyRepository.deleteAll();
        adminRepository.deleteAll();
        roleRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void create_olderThan24ownerLoggedAdmin_checkAccountCreated() throws Exception {
        CheckingAccDto checkingAccDto=new CheckingAccDto();
        checkingAccDto.setPrimaryOwnerId(accountHolderRepository.findAll().get(1).getId());
        checkingAccDto.setBalance(new BigDecimal("1200"));
        checkingAccDto.setSecretKey("password");
        String body = objectMapper.writeValueAsString(checkingAccDto);
        MvcResult result = mockMvc.perform(
                post("/admin/create-checkAcc")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user("botin")
                                .password("santander")
                                .roles("ADMIN"))
        ).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("1200"));
    }

    @Test
    void create_olderThan24ownerNoLoggedAdmin_checkAccountNoCreated() throws Exception {
        CheckingAccDto checkingAccDto=new CheckingAccDto();
        checkingAccDto.setPrimaryOwnerId(accountHolderRepository.findAll().get(1).getId());
        checkingAccDto.setBalance(new BigDecimal("1200"));
        checkingAccDto.setSecretKey("password");
        String body = objectMapper.writeValueAsString(checkingAccDto);
        MvcResult result = mockMvc.perform(
                post("/admin/create-checkAcc")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user("botin")
                                .password("santander")
                                .roles("ACCOUNT_HOLDER"))
        ).andExpect(status().isForbidden()).andReturn();
    }

    @Test
    void create_InvalidDtoLoggedAdmin_checkAccountNoCreated() throws Exception {
        CheckingAccDto checkingAccDto=new CheckingAccDto();
        checkingAccDto.setPrimaryOwnerId(accountHolderRepository.findAll().get(1).getId());
        checkingAccDto.setBalance(new BigDecimal("-1200"));
        checkingAccDto.setSecretKey("password");
        String body = objectMapper.writeValueAsString(checkingAccDto);
        MvcResult result = mockMvc.perform(
                post("/admin/create-checkAcc")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user("botin")
                                .password("santander")
                                .roles("ADMIN"))
        ).andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    void create_youngerThan24ownerLoggedAdmin_checkAccountCreated() throws Exception {
        CheckingAccDto checkingAccDto=new CheckingAccDto();
        checkingAccDto.setPrimaryOwnerId(accountHolderRepository.findAll().get(0).getId());
        checkingAccDto.setBalance(new BigDecimal("1200"));
        checkingAccDto.setSecretKey("password");
        String body = objectMapper.writeValueAsString(checkingAccDto);
        MvcResult result = mockMvc.perform(
                post("/admin/create-checkAcc")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user("botin")
                                .password("santander")
                                .roles("ADMIN"))
        ).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("1200"));
    }

    @Test
    void create_youngerThan24ownerNoLoggedAdmin_checkAccountNoCreated() throws Exception {
        CheckingAccDto checkingAccDto=new CheckingAccDto();
        checkingAccDto.setPrimaryOwnerId(accountHolderRepository.findAll().get(0).getId());
        checkingAccDto.setBalance(new BigDecimal("1200"));
        checkingAccDto.setSecretKey("password");
        String body = objectMapper.writeValueAsString(checkingAccDto);
        MvcResult result = mockMvc.perform(
                post("/admin/create-checkAcc")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user("botin")
                                .password("santander")
                                .roles("ACCOUNT_HOLDER"))
        ).andExpect(status().isForbidden()).andReturn();
    }

    @Test
    void create_InvalidDtoLoggedAdmin_checkAccountDidNotCreated() throws Exception {
        CheckingAccDto checkingAccDto=new CheckingAccDto();
        checkingAccDto.setPrimaryOwnerId(accountHolderRepository.findAll().get(0).getId());
        checkingAccDto.setBalance(new BigDecimal("-1200"));
        checkingAccDto.setSecretKey("password");
        String body = objectMapper.writeValueAsString(checkingAccDto);
        MvcResult result = mockMvc.perform(
                post("/admin/create-checkAcc")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user("botin")
                                .password("santander")
                                .roles("ADMIN"))
        ).andExpect(status().isBadRequest()).andReturn();
    }


}