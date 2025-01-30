package com.secondhandtrade.controller;

import com.secondhandtrade.model.Transaction;
import com.secondhandtrade.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

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
        return transactionService.save(transaction);
    }

    //删除订单
    @DeleteMapping
    public void deleteTransaction(@RequestBody Transaction transaction) {
        transactionService.deleteTransactionById(transaction.getTransactionId());
    }


}
