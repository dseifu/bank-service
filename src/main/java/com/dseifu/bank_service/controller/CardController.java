package com.dseifu.bank_service.controller;

import com.dseifu.bank_service.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping("/verify/card")
    public HashMap<String, String> findByCardNumberAndCcv(@RequestBody MultiValueMap<String, String> formParameters, HttpServletRequest httpServletRequest)
    {
        httpServletRequest.setAttribute("sessionId",formParameters.getFirst("sessionId"));
        return  cardService.findByCardNumberAndCcv(formParameters.getFirst("cardNumber"),formParameters.getFirst("ccv"), httpServletRequest);
    }

    @PostMapping("/changeAuthenticationPreference")
    public String changeAuthenticationPreference(@RequestBody MultiValueMap<String, String> formParameters, HttpServletRequest httpServletRequest){
        httpServletRequest.setAttribute("isAuthenticated",formParameters.getFirst("isAuthenticated"));
        return  cardService.changeAuthenticationPreference(formParameters.getFirst("cardNumber"),formParameters.getFirst("preference"), httpServletRequest);
    }
}
