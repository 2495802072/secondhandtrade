package com.secondhandtrade.repository;

import com.secondhandtrade.model.Transaction;
import com.secondhandtrade.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findTransactionByTransactionId(Long transactionId);

    List<Transaction> findTransactionByBuyer(User buyer);
}
