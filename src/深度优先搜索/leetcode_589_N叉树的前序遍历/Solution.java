package 深度优先搜索.leetcode_589_N叉树的前序遍历;

import 深度优先搜索.Node;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> preorder(Node root) {
        if(root == null) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        res.add(root.val);
        for (Node child : root.children) {
            List<Integer> list = preorder(child);
            res.addAll(list);
        }
        return res;
    }
}
