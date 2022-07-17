package 剑指offer.leetcode_剑指Offer36二叉搜索树与双向链表;

import 剑指offer.Node;

public class Solution {
    public Node treeToDoublyList(Node root) {
        if(root == null) return null;
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    private Node pre , head;

    private void dfs(Node root){
        if(root == null) return;

        dfs(root.left);
        if(pre == null) head = root;
        else {
            pre.right = root;
        }
        root.left = pre;
        pre = root;
        dfs(root.right);
    }
}
