package com.secondhandtrade.controller;

import com.secondhandtrade.service.FPService;
import com.secondhandtrade.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/fp")
public class FPController {

    @Autowired
    private FPService fpService;

    @Autowired
    private TransactionService transactionService;

    /**
     * 获取频繁项集
     *
     * @param minSupport   最小支持度
     * @return 返回频繁项集及其支持度
     */
    @PostMapping("/frequent-itemsets")
    public ResponseEntity<Map<List<String>, Double>> getFrequentItemsets(
            @RequestBody double minSupport) {
        List<List<String>> transactions = transactionService.getTransactionsForFP();

        Map<List<String>, Double> frequentItemsets = fpService.getFrequentItemsets(transactions, minSupport);
        return ResponseEntity.ok(frequentItemsets);
    }
}
