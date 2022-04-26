package com.dseifu.bank_service.controller;

import com.dseifu.bank_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/deposit")
    public String deposit(@RequestBody MultiValueMap<String, String> formParameters){
        return  accountService.deposit(formParameters.getFirst("cardNumber"), formParameters.getFirst("amount"), formParameters.getFirst("isAuthenticated"));
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestBody MultiValueMap<String, String> formParameters){
        return  accountService.withdraw(formParameters.getFirst("cardNumber"), formParameters.getFirst("amount"), formParameters.getFirst("isAuthenticated"));
    }

    @PostMapping("/checkBalance")
    public String checkBalance(@RequestBody MultiValueMap<String, String> formParameters){
        return  accountService.checkBalance(formParameters.getFirst("cardNumber"), formParameters.getFirst("isAuthenticated"));
    }
}
