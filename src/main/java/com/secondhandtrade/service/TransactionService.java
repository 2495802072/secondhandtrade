package com.secondhandtrade.service;

import com.secondhandtrade.model.Transaction;
import com.secondhandtrade.model.User;
import com.secondhandtrade.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction save(Transaction transaction) {
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setUpdatedAt(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public void deleteTransactionById(Long id) {
        transactionRepository.deleteById(id);
    }

    public List<Transaction> getTransactionsByBuyer(User buyer) {
        return transactionRepository.findTransactionByBuyer(buyer);
    }

    public List<Transaction> getTransactionsBySeller(User seller) {
        return transactionRepository.findTransactionBySeller(seller);
    }
}
