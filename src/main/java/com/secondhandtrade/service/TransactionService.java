package com.secondhandtrade.service;

import com.secondhandtrade.model.Product;
import com.secondhandtrade.model.Transaction;
import com.secondhandtrade.model.User;
import com.secondhandtrade.repository.ProductRepository;
import com.secondhandtrade.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ProductRepository productRepository;

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

    public List<Transaction> getTransactionsByProductTitle(String title) {
        // 根据商品标题模糊查询获取商品列表
        List<Product> listProduct = productRepository.findByTitleContaining(title);

        // 提取商品ID列表
        List<Long> productIds = listProduct.stream()
                .map(Product::getProductId)
                .collect(Collectors.toList());

        // 根据商品ID列表查询交易记录
        return transactionRepository.findByProductIdIn(productIds);
    }

    public List<Transaction> getTransactionsByProduct(Product product) {
        return transactionRepository.findByProduct(product);
    }

    public Transaction patchTransaction(Long id, Map<String, Object> updates) {
        Transaction Transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        // 动态更新字段（使用反射或手动判断）
        updates.forEach((key, value) -> {
            switch (key) {
                case "address":
                    Transaction.setAddress((String) value);
                    break;
                case "note":
                    Transaction.setNote((String) value);
                    break;
                case "status":
                    Transaction.setStatus((String) value);
                    break;
                case "updatedAt":
                    Transaction.setUpdatedAt(LocalDateTime.now());
            }
        });

        return transactionRepository.save(Transaction);
    }
}
