package com.dseifu.bank_service.service;

import com.dseifu.bank_service.entity.Card;
import com.dseifu.bank_service.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;


@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public HashMap<String, String> findByCardNumberAndCcv(String cardNumber, String ccv, HttpServletRequest request) {
        HashMap<String, String> statusAndPreference = new HashMap<>();
        statusAndPreference.put("status","Verified");
        Card card = cardRepository.findByCardNumberAndCcv(cardNumber,ccv);
        if(card != null) {
            statusAndPreference.put("preference",card.getPreferredAuthentication());
            if(new Date().after(card.getExpiryDate()))
                statusAndPreference.put("status","Expired");
            else if(card.isBlocked())
                statusAndPreference.put("status","Blocked");
        }
        else
            statusAndPreference.put("status","Unknown card");
        if(statusAndPreference.get("status") == "Verified") {
            request.getSession().setAttribute("isVerified","true");
        }
        return  statusAndPreference;
    }

    public String changeAuthenticationPreference(String cardNumber, String preference, HttpServletRequest request) {
        Object isAuthenticated = request.getSession().getAttribute("isAuthenticated");
        if(isAuthenticated != null && isAuthenticated.equals("true")){
            Card card = cardRepository.findByCardNumber(cardNumber);
            try{
                card.setPreferredAuthentication(preference);
                cardRepository.save(card);
                return "Success";
            }
            catch (Exception e){
                return e.toString();
            }
        }
        else
            return "Not Authenticated";

    }
}
