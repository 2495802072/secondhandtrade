package com.secondhandtrade.repository;

import com.secondhandtrade.model.Transaction;
import com.secondhandtrade.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findTransactionByBuyer(User buyer);
    List<Transaction> findTransactionBySeller(User seller);
}
