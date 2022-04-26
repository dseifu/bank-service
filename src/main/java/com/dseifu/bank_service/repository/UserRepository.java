package com.dseifu.bank_service.repository;

import com.dseifu.bank_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByCardId(int cardId);
}
