package com.secondhandtrade.repository;

import com.secondhandtrade.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findTransactionByTransactionId(Long transactionId);
}
