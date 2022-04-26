package com.dseifu.bank_service.repository;

import com.dseifu.bank_service.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    Card findByCardNumberAndCcv(String cardNumber, String ccv);

    Card findByCardNumber(String cardNumber);
}
