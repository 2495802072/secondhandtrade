package com.secondhandtrade;

import com.secondhandtrade.FP.FP;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FunctionTest {
    public static void main(String[] args) {
        List<List<String>> transactions = Arrays.asList(
                Arrays.asList("商品A", "商品B", "商品C"),
                Arrays.asList("商品A", "商品B"),
                Arrays.asList("商品A", "商品C")
        );

        double minSupport = 0.5; // 最小支持度

        Map<List<String>, Double> frequentItemsets = FP.FPGrowth(transactions, minSupport);

        // 输出频繁项集及其支持度
        frequentItemsets.forEach((itemset, support) -> {
            System.out.println(itemset + " : " + support);
        });

    }
}
