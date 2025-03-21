package com.secondhandtrade.service;

import com.secondhandtrade.FP.FP;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FPService {

    /**
     * 调用 FP-Growth 算法，获取频繁项集
     *
     * @param transactions 交易数据，每个交易是一个商品列表
     * @param minSupport   最小支持度
     * @return 返回频繁项集及其支持度
     */
    public Map<List<String>, Double> getFrequentItemsets(List<List<String>> transactions, double minSupport) {
        return FP.FPGrowth(transactions, minSupport);
    }
}
