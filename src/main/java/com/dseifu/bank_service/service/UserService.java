package com.dseifu.bank_service.service;

import com.dseifu.bank_service.entity.Card;
import com.dseifu.bank_service.entity.User;
import com.dseifu.bank_service.repository.CardRepository;
import com.dseifu.bank_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardRepository cardRepository;

    public String authenticateByPin(String pin, String cardNumber, String isVerified) {
        if(isVerified != null && isVerified.equals("true")) {
            try {
                Card card = cardRepository.findByCardNumber(cardNumber);
                User user = userRepository.findByCardId(card.getCardId());
                if(pin.equals(user.getPin())){
                    if(user.getNumberOfTrials()<3){
                        user.setNumberOfTrials(0);
                        userRepository.save(user);
                        return "Authenticated";
                    }
                    else
                        return "Blocked";
                }
                else {
                    user.setNumberOfTrials(user.getNumberOfTrials()+1);
                    userRepository.save(user);
                    if(user.getNumberOfTrials()>=3) {
                        card.setBlocked(true);
                        cardRepository.save(card);
                        return "Blocked";
                    }
                    return "Not Authenticated";
                }

            } catch (Exception e) {
                return e.toString();
            }
        }
        else
            return "Not Verified";

    }

    public String authenticateByFingerPrint(String fingerPrint, String cardNumber, String isVerified) {
        if(isVerified != null && isVerified.equals("true")) {
            try {
                Card card = cardRepository.findByCardNumber(cardNumber);
                User user = userRepository.findByCardId(card.getCardId());
                if(fingerPrint.equals(user.getFingerPrint())){
                    if(user.getNumberOfTrials()<3){
                        user.setNumberOfTrials(0);
                        userRepository.save(user);
                        return "Authenticated";
                    }
                    else
                        return "Blocked";
                }
                else {
                    user.setNumberOfTrials(user.getNumberOfTrials()+1);
                    userRepository.save(user);
                    if(user.getNumberOfTrials()>=3) {
                        card.setBlocked(true);
                        cardRepository.save(card);
                        return "Blocked";
                    }
                    return "Not Authenticated";
                }

            } catch (Exception e) {
                return e.toString();
            }
        }
        else
            return "Not Verified";
    }
}
