package com.dseifu.bank_service.service;

import com.dseifu.bank_service.entity.Account;
import com.dseifu.bank_service.entity.Card;
import com.dseifu.bank_service.entity.User;
import com.dseifu.bank_service.repository.AccountRepository;
import com.dseifu.bank_service.repository.CardRepository;
import com.dseifu.bank_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UserRepository userRepository;

    public String deposit(String cardNumber, String amount, HttpServletRequest request) {
        Object isAuthenticated = request.getSession().getAttribute("isAuthenticated");
        if(isAuthenticated != null && isAuthenticated.equals("true")){
            try {
                Card card = cardRepository.findByCardNumber(cardNumber);
                User user = userRepository.findByCardId(card.getCardId());
                Account account = accountRepository.findByUserId(user.getUserId());
                account.setBalance(account.getBalance()+Float.parseFloat(amount));
                accountRepository.save(account);
                return "Success";
            } catch (Exception e){
                return e.toString();
            }
        }
        else
            return "Not Authenticated";

    }

    public String withdraw(String cardNumber, String amount, HttpServletRequest request) {
        Object isAuthenticated = request.getSession().getAttribute("isAuthenticated");
        if(isAuthenticated != null && isAuthenticated.equals("true")){
            try {
                Card card = cardRepository.findByCardNumber(cardNumber);
                User user = userRepository.findByCardId(card.getCardId());
                Account account = accountRepository.findByUserId(user.getUserId());
                Float a = Float.parseFloat(amount);
                if(account.getBalance()>=a){
                    account.setBalance(account.getBalance()-a);
                    accountRepository.save(account);
                    return "Success";
                }
                else
                    return "Small Balance";
            } catch(Exception e){
                return e.toString();
            }
        }
        else
            return "Not Authenticated";
    }

    public String checkBalance(String cardNumber, HttpServletRequest request) {
        Object isAuthenticated = request.getSession().getAttribute("isAuthenticated");
        if(isAuthenticated != null && isAuthenticated.equals("true")){
            try {
                Card card = cardRepository.findByCardNumber(cardNumber);
                User user = userRepository.findByCardId(card.getCardId());
                Account account = accountRepository.findByUserId(user.getUserId());
                return account.getBalance()+"";
            } catch(Exception e){
                return e.toString();
            }
        }
        else
            return "Not Authenticated";

    }
}
