package com.secondhandtrade.repository;

import com.secondhandtrade.model.Contacts;
import com.secondhandtrade.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactsRepository extends JpaRepository<Contacts, Long> {

    boolean existsByUserAndRecipient(User user,User recipient);
    List<Contacts> findByUser(User user);

}
