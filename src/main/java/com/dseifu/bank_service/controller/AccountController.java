package com.dseifu.bank_service.controller;

import com.dseifu.bank_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/deposit")
    public String deposit(@RequestBody MultiValueMap<String, String> formParameters, HttpServletRequest httpServletRequest){
        httpServletRequest.setAttribute("isAuthenticated",formParameters.getFirst("isAuthenticated"));
        return  accountService.deposit(formParameters.getFirst("cardNumber"), formParameters.getFirst("amount"), httpServletRequest);
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestBody MultiValueMap<String, String> formParameters, HttpServletRequest httpServletRequest){
        httpServletRequest.setAttribute("isAuthenticated",formParameters.getFirst("isAuthenticated"));
        return  accountService.withdraw(formParameters.getFirst("cardNumber"), formParameters.getFirst("amount"), httpServletRequest);
    }

    @PostMapping("/checkBalance")
    public String checkBalance(@RequestBody MultiValueMap<String, String> formParameters, HttpServletRequest httpServletRequest){
        httpServletRequest.setAttribute("isAuthenticated",formParameters.getFirst("isAuthenticated"));
        return  accountService.checkBalance(formParameters.getFirst("cardNumber"), httpServletRequest);
    }
}
