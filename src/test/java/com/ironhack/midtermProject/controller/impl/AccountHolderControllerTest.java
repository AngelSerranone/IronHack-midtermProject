package com.ironhack.midtermProject.controller.impl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.midtermProject.classes.Address;
import com.ironhack.midtermProject.classes.Money;
import com.ironhack.midtermProject.controller.dto.users.AccountHolderDto;
import com.ironhack.midtermProject.enums.SystemRole;
import com.ironhack.midtermProject.model.AccountHolder;
import com.ironhack.midtermProject.model.Admin;
import com.ironhack.midtermProject.model.CheckingAcc;
import com.ironhack.midtermProject.model.Role;
import com.ironhack.midtermProject.repository.AccountHolderRepository;
import com.ironhack.midtermProject.repository.AccountRepository;
import com.ironhack.midtermProject.repository.AdminRepository;
import com.ironhack.midtermProject.repository.RoleRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AccountHolderControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AdminRepository adminRepository;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        Admin admin1= new Admin("Emilio Botín", "botin", "santander");
        adminRepository.save(admin1);
        roleRepository.save(new Role(SystemRole.ADMIN, admin1));
    }

    @AfterEach
    void tearDown() {
        roleRepository.deleteAll();
        adminRepository.deleteAll();
    }

    @Test
    void create_ValidDtoWithAdminLogged_newAccHolder() throws Exception {
        AccountHolderDto accountHolderDTO = new AccountHolderDto();
        accountHolderDTO.setName("Pedro");
        accountHolderDTO.setUsername("pedrogolosho");
        accountHolderDTO.setPassword("12345");
        accountHolderDTO.setDateOfBirth(LocalDate.of(1985,4,2));
        accountHolderDTO.setPrimaryAddress("C/ hola que tal, 3.");
        accountHolderDTO.setMailingAddress("C/ hola que tal, 67.");
        String body = objectMapper.writeValueAsString(accountHolderDTO);
        System.out.println(body);
        MvcResult result = mockMvc.perform(
                post("/admin/create-account-holder")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user("botin")
                                .password("santander")
                                .roles("ADMIN"))
        ).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Pedro"));
    }

    @Test
    void create_ValidDtoWithNoAdminLogged_NoAccHolder() throws Exception {
        AccountHolderDto accountHolderDTO = new AccountHolderDto();
        accountHolderDTO.setName("Pedro");
        accountHolderDTO.setUsername("pedrogolosho");
        accountHolderDTO.setPassword("12345");
        accountHolderDTO.setDateOfBirth(LocalDate.of(1985,4,2));
        accountHolderDTO.setPrimaryAddress("C/ hola que tal, 3.");
        accountHolderDTO.setMailingAddress("C/ hola que tal, 67.");
        String body = objectMapper.writeValueAsString(accountHolderDTO);
        System.out.println(body);
        MvcResult result = mockMvc.perform(
                post("/admin/create-account-holder")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user("botin")
                                .password("santander")
                                .roles("ACCOUNT_HOLDER"))
        ).andExpect(status().isForbidden()).andReturn();
    }

    @Test
    void create_ValidDtoWithNoUserLogged_NoAccHolder() throws Exception {
        AccountHolderDto accountHolderDTO = new AccountHolderDto();
        accountHolderDTO.setName("Pedro");
        accountHolderDTO.setUsername("pedrogolosho");
        accountHolderDTO.setPassword("12345");
        accountHolderDTO.setDateOfBirth(LocalDate.of(1985,4,2));
        accountHolderDTO.setPrimaryAddress("C/ hola que tal, 3.");
        accountHolderDTO.setMailingAddress("C/ hola que tal, 67.");
        String body = objectMapper.writeValueAsString(accountHolderDTO);
        System.out.println(body);
        MvcResult result = mockMvc.perform(
                post("/admin/create-account-holder")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isUnauthorized()).andReturn();
    }

    @Test
    void create_invalidDto_NoAccHolder() throws Exception {
        AccountHolderDto accountHolderDTO = new AccountHolderDto();
        accountHolderDTO.setName("Pedro");
        accountHolderDTO.setDateOfBirth(LocalDate.of(1985,4,2));
        accountHolderDTO.setPrimaryAddress("C/ hola que tal, 3.");
        accountHolderDTO.setMailingAddress("C/ hola que tal, 67.");
        String body = objectMapper.writeValueAsString(accountHolderDTO);
        System.out.println(body);
        MvcResult result = mockMvc.perform(
                post("/admin/create-account-holder")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user("botin")
                                .password("santander")
                                .roles("ACCOUNT_HOLDER"))
        ).andExpect(status().isForbidden()).andReturn();
    }



}