package com.secondhandtrade.controller;

import com.secondhandtrade.model.Product;
import com.secondhandtrade.model.Transaction;
import com.secondhandtrade.model.User;
import com.secondhandtrade.repository.ProductRepository;
import com.secondhandtrade.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Transaction> getAllTransactions() {
        System.out.println(1);
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Transaction transaction = transactionService.getTransactionById(id);
        if (transaction == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transaction);
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.save(transaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransactionById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/buyer/{buyerId}")
    public List<Transaction> getTransactionsByBuyer(@PathVariable Long buyerId) {
        User buyer = new User();
        buyer.setUserId(buyerId);
        return transactionService.getTransactionsByBuyer(buyer);
    }

    @GetMapping("/seller/{sellerId}")
    public List<Transaction> getTransactionsBySeller(@PathVariable Long sellerId) {
        User seller = new User();
        seller.setUserId(sellerId);
        return transactionService.getTransactionsBySeller(seller);
    }

    @PostMapping("/productTitle")
    public ResponseEntity<List<Transaction>> getTransactionsByProductTitle(@RequestBody String title) {
        // 调用服务层方法获取交易记录
        List<Transaction> transactions = transactionService.getTransactionsByProductTitle(title);

        // 返回响应实体
        return ResponseEntity.ok(transactions);
    }


    @GetMapping("/byProduct/{productId}")
    public ResponseEntity<List<Transaction>> getTransactionsByProductId(@PathVariable Long productId) {
        Product product = new Product();
        product.setProductId(productId);
        List<Transaction> transactions = transactionService.getTransactionsByProduct(product);
        return ResponseEntity.ok(transactions);
    }
}
