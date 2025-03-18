package com.secondhandtrade.controller;

import com.secondhandtrade.model.Product;
import com.secondhandtrade.model.Transaction;
import com.secondhandtrade.model.User;
import com.secondhandtrade.service.ProductService;
import com.secondhandtrade.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private ProductService productService;

    //查询订单
    @GetMapping()
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public Transaction getTransaction(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }


    //增加/更新订单
    @PostMapping()
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        Product p = transaction.getProduct();
        p.setStatus("已售出");
        productService.save(p);
        return transactionService.save(transaction);
    }

    //删除订单
    @DeleteMapping
    public void deleteTransaction(@RequestBody Transaction transaction) {
        transactionService.deleteTransactionById(transaction.getTransactionId());
    }

    @PostMapping("/user")
    public ResponseEntity<?> findTransactionByUser(@RequestBody Transaction transaction) {
        User buyer = transaction.getBuyer();
        if (buyer != null) {
            List<Transaction> transactions = transactionService.getTransactionsByBuyer(buyer);
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("用户不存在",HttpStatus.NOT_FOUND);
        }
    }


}
