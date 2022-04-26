package com.dseifu.bank_service.controller;

import com.dseifu.bank_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/Authenticate/pin")
    public String authenticateByPin(@RequestBody MultiValueMap<String, String> formParameters, HttpServletRequest httpServletRequest){
        httpServletRequest.setAttribute("isVerified",formParameters.getFirst("isVerified"));
        return  userService.authenticateByPin(formParameters.getFirst("pin"), formParameters.getFirst("cardNumber"), httpServletRequest);
    }

    @PostMapping("/Authenticate/fingerPrint")
    public String authenticateByFingerPrint(@RequestBody MultiValueMap<String, String> formParameters, HttpServletRequest httpServletRequest){
        httpServletRequest.setAttribute("isVerified",formParameters.getFirst("isVerified"));
        return  userService.authenticateByFingerPrint(formParameters.getFirst("fingerPrint"), formParameters.getFirst("cardNumber"), httpServletRequest);
    }
    @PostMapping("/done")
    public String done(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().invalidate();
        return "Closed";
    }
}
