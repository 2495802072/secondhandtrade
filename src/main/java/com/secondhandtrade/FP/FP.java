package com.secondhandtrade.FP;

import java.util.*;

public class FP {

    // 记录总交易数量
    static double totalNum;
    // 二维List记录交易
    static List<List<String>> transactions = new ArrayList<>();
    // 使用map统计某一商品的被交易数量
    static Map<String, Integer> commodity = new HashMap<>();

    /**
     * FP-Growth 算法实现
     *
     * @param transactions 交易数据，每个交易是一个商品列表
     * @param minSupport   最小支持度
     * @return 返回频繁项集及其支持度
     */
    public static Map<List<String>, Double> FPGrowth(List<List<String>> transactions, double minSupport) {
        // 初始化总交易数量
        totalNum = transactions.size();

        // 统计每个商品的交易数量
        for (List<String> transaction : transactions) {
            for (String item : transaction) {
                commodity.put(item, commodity.getOrDefault(item, 0) + 1);
            }
        }

        // 获取频繁的单一商品
        List<String> frequentCommodity = new ArrayList<>();
        List<String> infrequentCommodity = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : commodity.entrySet()) {
            if (entry.getValue() >= minSupport * totalNum) {
                frequentCommodity.add(entry.getKey());
            } else {
                infrequentCommodity.add(entry.getKey());
            }
        }

        // 清理不频繁事务
        List<List<String>> frequentTransaction = new ArrayList<>();
        for (List<String> transaction : transactions) {
            List<String> cleanedTransaction = new ArrayList<>(transaction);
            cleanedTransaction.removeAll(infrequentCommodity);
            if (!cleanedTransaction.isEmpty()) {
                frequentTransaction.add(cleanedTransaction);
            }
        }

        // 按照支持度排序
        for (List<String> transaction : frequentTransaction) {
            // 根据商品的支持度获取比较器
            Comparator<String> comparator = Comparator.comparingDouble(commodity::get);

            // 将比较器反转以实现降序排序
            Comparator<String> reversedComparator = comparator.reversed();

            // 对交易中的商品进行排序
            transaction.sort(reversedComparator);
        }

        // 新建标题表，生成FP树
        Map<String, TreeNode> headerTable = new HashMap<>();
        TreeNode fptree = buildFPTree(frequentTransaction, headerTable);

        // headertable 与 support 值相关联
        Map<String, Double> headerSup = headertableSup(headerTable, totalNum);

        Map<List<String>, Double> generateFrequentPatterns = new HashMap<>();

        // 添加 FrequentPattern，它由 headerSup 的一个元素组成
        for (Map.Entry<String, Double> entry : headerSup.entrySet()) {
            String item = entry.getKey();
            double sup = entry.getValue();
            List<String> oneElementPattern = new ArrayList<>();
            oneElementPattern.add(item);
            generateFrequentPatterns.put(oneElementPattern, sup);
        }

        // 构建条件FP_tree并获取频繁模式
        for (Map.Entry<String, TreeNode> item : headerTable.entrySet()) {
            TreeNode node = item.getValue();
            for (Map.Entry<List<String>, Double> generateEntry : generateFrequentPattern(node, totalNum, minSupport).entrySet()) {
                generateFrequentPatterns.put(generateEntry.getKey(), generateEntry.getValue());
            }
        }

        // 返回频繁项集及其支持度
        return generateFrequentPatterns;
    }

    // 构建具有频繁事务的 FP 树或使用 Frequent 模式构建条件 FP 树
    private static TreeNode buildFPTree(List<List<String>> frequentTransactions, Map<String, TreeNode> headerTable) {
        TreeNode root = new TreeNode(null, null, 0);
        for (List<String> transaction : frequentTransactions) {
            TreeNode currentNode = root;
            for (String item : transaction) {
                if (currentNode.hasChild(item)) {
                    currentNode = currentNode.getChild(item);
                    currentNode.incrementCount();
                } else {
                    TreeNode newNode = new TreeNode(item, currentNode, 1);
                    currentNode.addChild(newNode);
                    currentNode = newNode;

                    // 更新标题表
                    if (headerTable.containsKey(item)) {
                        TreeNode lastNode = headerTable.get(item);
                        while (lastNode.getNextNode() != null) {
                            lastNode = lastNode.getNextNode();
                        }
                        lastNode.setNextNode(newNode);
                    } else {
                        headerTable.put(item, newNode);
                    }
                }
            }
        }

        return root;
    }

    // 关联表标题 使得新的表标题与支持度关联
    private static Map<String, Double> headertableSup(Map<String, TreeNode> headerTable, double numTransactions) {
        Map<String, Double> headerSup = new HashMap<>();
        for (Map.Entry<String, TreeNode> entry : headerTable.entrySet()) {
            String item = entry.getKey();
            TreeNode node = entry.getValue();
            double count = 0.0;
            while (node != null) {
                count += node.getCount();
                node = node.getNextNode();
            }
            count /= numTransactions;
            headerSup.put(item, count);
        }
        return headerSup;
    }

    // generateFrequent 模式方法
    // 重用 buildFPTree（）、headertableSup（） 生成条件 fp 树
    // 方法中，变量前面的 'Con' 表示 'conditional'
    private static Map<List<String>, Double> generateFrequentPattern(TreeNode item, double numTransactions, double minSupport) {
        Map<List<String>, Double> generateFrequentPatterns = new HashMap<>();

        // 频繁模式，每个项目保存在 frequentPatterns 上
        List<List<String>> frequentPatterns = TreeNode.frequentPatterns(item);

        // 构造条件 FP-tree
        Map<String, TreeNode> ConheaderTable = new HashMap<>();
        TreeNode Confptree = buildFPTree(frequentPatterns, ConheaderTable);

        Map<String, Double> ConheaderSup = headertableSup(ConheaderTable, numTransactions);

        List<String> frequentPattern = new ArrayList<>();
        Map<String, Double> itemSupports = new HashMap<>(); // 保存支持度

        for (Map.Entry<String, Double> frequentItem : ConheaderSup.entrySet()) {
            if (frequentItem.getValue() >= minSupport) {
                frequentPattern.add(frequentItem.getKey());
                itemSupports.put(frequentItem.getKey(), frequentItem.getValue());
            }
        }

        // 当 frequentPattern 找到（不为空 frequentpattern） 时
        // 使用 SubsetGenerator（） 使在此方法上运行的 item 成为可能的子集
        // Count frequentSubset 模式支持值
        // 在 generateFrequnetPatterns 中放置 subset 和支持值
        if (!frequentPattern.isEmpty()) {
            List<List<String>> frequentSubset = TreeNode.Subsetgenerator(frequentPattern, item.getItemName());
            for (List<String> subset : frequentSubset) {
                Double supportValue = TreeNode.calculateSupportValueForSubset(subset, itemSupports, numTransactions);
                generateFrequentPatterns.put(subset, supportValue);
            }
        }

        return generateFrequentPatterns;
    }

}
