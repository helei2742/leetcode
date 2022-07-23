package 剑指offer.leetcode_剑指Offer68I_二叉搜索树的最近公共祖先;

import 剑指offer.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        LinkedList<TreeNode> path1 = findPath(root, p);
        LinkedList<TreeNode> path2 = findPath(root, q);
        path1.forEach(treeNode -> System.out.print(treeNode.val+"-"));
        System.out.println("\n");
        path2.forEach(treeNode -> System.out.print(treeNode.val+"-"));

        TreeNode res = root;
        while (!path1.isEmpty() || !path2.isEmpty()){
            if(path1.isEmpty()||path2.isEmpty()){
                break;
            }
            TreeNode first1 = path1.getFirst();
            TreeNode first2 = path2.getFirst();
            if(first1.val == first2.val){
                res = first1;
                path1.removeFirst();
                path2.removeFirst();
            }else {
                break;
            }
        }
        System.out.println(res);
        return res;
    }
    private LinkedList<TreeNode> findPath(TreeNode root, TreeNode tar){
        LinkedList<TreeNode> res = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null){
            if(cur.val == tar.val){
                res.addLast(cur);
                break;
            }else if(cur.val > tar.val){
                res.addLast(cur);
                cur = cur.left;
            }else {
                res.addLast(cur);
                cur = cur.right;
            }
        }
        return res;
    }
}
