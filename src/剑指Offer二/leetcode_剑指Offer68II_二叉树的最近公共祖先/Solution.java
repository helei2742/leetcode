package 剑指Offer二.leetcode_剑指Offer68II_二叉树的最近公共祖先;

import 剑指offer.TreeNode;

import java.util.*;

public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
/*        dfs(root, p, new LinkedList<>());
        dfs(root, q, new LinkedList<>());

        LinkedList<TreeNode> path1 = map.get(p);
        LinkedList<TreeNode> path2 = map.get(q);

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
        return res;*/
        fn(root,p,q);
        return ans;
    }
    private TreeNode ans ;

    /**
     * 表示，root节点是否包含p节点或q节点
     *  一个节点是最近公共祖先有两种情况，
     *      1、左子树包含p或q，右子树也包含p或q bL&&bR
     *      2、当前节点是p或q， 左子树或右子shu中含p或q ((p.val == root.val|| q.val == root.val) && (bL||bR))
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    private boolean fn(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return false;
        boolean bL = fn(root.left, p, q);
        boolean bR = fn(root.right, p, q);
        if((bL&&bR) || ((p.val == root.val|| q.val == root.val) && (bL||bR))){
            this.ans = root;
        }
        return bL || bR || p.val == root.val || q.val == root.val;
    }

    private Map<TreeNode, LinkedList<TreeNode>> map = new HashMap<>();
    private void dfs(TreeNode root, TreeNode tar, LinkedList<TreeNode> path){
        if(root==null) return;
        if(root.val == tar.val){
            path.add(root);
            map.put(tar, new LinkedList<>(path));
            return;
        }
        path.addLast(root);
        dfs(root.left, tar, path);
        dfs(root.right, tar, path);
        path.removeLast();
    }
}
