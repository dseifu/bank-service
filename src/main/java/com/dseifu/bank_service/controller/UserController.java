package com.dseifu.bank_service.controller;

import com.dseifu.bank_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/Authenticate/pin")
    public String authenticateByPin(@RequestBody MultiValueMap<String, String> formParameters){
        return  userService.authenticateByPin(formParameters.getFirst("pin"), formParameters.getFirst("cardNumber"), formParameters.getFirst("isVerified"));
    }

    @PostMapping("/Authenticate/fingerPrint")
    public String authenticateByFingerPrint(@RequestBody MultiValueMap<String, String> formParameters){
        return  userService.authenticateByFingerPrint(formParameters.getFirst("fingerPrint"), formParameters.getFirst("cardNumber"), formParameters.getFirst("isVerified"));
    }
}
