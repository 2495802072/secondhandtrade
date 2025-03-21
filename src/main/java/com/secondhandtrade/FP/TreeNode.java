package com.secondhandtrade.FP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

// FP树的节点类
public class TreeNode {
    private String itemName;
    private int count;
    private TreeNode parent;
    private List<TreeNode> children;
    private TreeNode nextNode;

    public TreeNode(String itemName, TreeNode parent, int count) {
        this.itemName = itemName;
        this.parent = parent;
        this.count = count;
        this.children = new ArrayList<>();
        this.nextNode = null;
    }

    public String getItemName() {
        return itemName;
    }

    public int getCount() {
        return count;
    }

    public TreeNode getParent() {
        return parent;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public TreeNode getNextNode() {
        return nextNode;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public void setNextNode(TreeNode nextNode) {
        this.nextNode = nextNode;
    }

    public boolean hasChild(String itemName) {
        for (TreeNode child : children) {
            if (child.getItemName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    public TreeNode getChild(String itemName) {
        for (TreeNode child : children) {
            if (child.getItemName().equals(itemName)) {
                return child;
            }
        }
        return null;
    }

    public void addChild(TreeNode childNode) {
        children.add(childNode);
    }

    public void incrementCount() {
        this.count++;
    }


    // 此方法在构建条件 fp-tree 时需要找到 频繁项目
    public static List<List<String>> frequentPatterns(TreeNode node) {
        List<List<String>> frequentPatterns = new ArrayList<>();
        while (node != null) {
            // 遍历节点树，添加
            for (int i = 0 ; i<node.count; i++){
                frequentPatterns.add(findPathToRoot(node));
            }
            node = node.getNextNode();
        }
        return frequentPatterns;
    }

    //遍历FP树的每个节点
    public static List<String> findPathToRoot(TreeNode node) {
        List<String> path = new ArrayList<>();
        TreeNode currentNode = node.getParent();

        while (currentNode != null && currentNode.getParent() != null) {
            path.add(currentNode.getItemName());
            currentNode = currentNode.getParent();
        }

        Collections.reverse(path);
        return path;
    }

    //子集生成器
    public static List<List<String>> Subsetgenerator(List<String> set, String element) {
        List<List<String>> subsets = new ArrayList<>();
        int setSize = set.size();

        //使所有子集成为可能
        for (int i = 0; i < (1 << setSize); i++) {
            List<String> subset = new ArrayList<>();
            for (int j = 0; j < setSize; j++) {
                if ((i & (1 << j)) != 0) {
                    subset.add(set.get(j));
                }
            }
            // 非空子集上添加元素
            if (!subset.isEmpty()) {
                subset.add(element);
                subsets.add(subset);
            }
        }

        //clean up with new arraylist
        List<String> fullSubset = new ArrayList<>(set);
        fullSubset.add(element);
        subsets.add(fullSubset);

        return subsets;
    }

    // 对 frequentSubset 模式支持值进行计数
    public static Double calculateSupportValueForSubset(List<String> subset, Map<String, Double> itemSupports, double numTransactions) {
        double sumSupport = 0;
        for (String item : subset) {
            sumSupport += itemSupports.getOrDefault(item, 0.0);
        }
        return sumSupport;
    }
}
