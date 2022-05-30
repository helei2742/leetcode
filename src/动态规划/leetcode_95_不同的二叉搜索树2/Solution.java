package 动态规划.leetcode_95_不同的二叉搜索树2;


import 深度优先搜索.TreeNode;

import java.util.ArrayList;
import java.util.List;


public class Solution {
    public List<TreeNode> generateTrees(int n) {
        if(n == 0){
            return new ArrayList<>();
        }
        return find(1, n);

    }
    private List<TreeNode> find(int start, int end) {
        List<TreeNode> trees = new ArrayList<>();
        if(start>end) {
            trees.add(null);
            return trees;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> left = find(start, i - 1);
            List<TreeNode> right = find(i + 1, end);

            for (TreeNode leftNode : left) {
                for (TreeNode rightNode : right) {
                    TreeNode newTree = new TreeNode(i);
                    newTree.left = leftNode;
                    newTree.right = rightNode;
                    trees.add(newTree);
                }
            }
        }
        return trees;
    }
}
