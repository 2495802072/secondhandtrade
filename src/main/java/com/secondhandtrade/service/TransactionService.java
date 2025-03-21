package com.secondhandtrade.service;

import com.secondhandtrade.model.Transaction;
import com.secondhandtrade.model.User;
import com.secondhandtrade.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    /**
     * 获取所有交易数据，并转换为 FP-Growth 所需的格式
     *
     * @return 返回交易数据，每个交易是一个商品列表
     */
    public List<List<String>> getTransactionsForFP() {
        List<Transaction> transactions = transactionRepository.findAll();
        List<List<String>> transactionData = new ArrayList<>();

        for (Transaction transaction : transactions) {
            List<String> items = new ArrayList<>();
            // 假设每个 Transaction 对象有一个 getItems() 方法，返回商品列表
            items.addAll(transaction.getItems());
            transactionData.add(items);
        }

        return transactionData;
    }
}
