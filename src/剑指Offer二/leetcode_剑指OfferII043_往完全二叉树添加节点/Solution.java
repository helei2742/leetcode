package 剑指Offer二.leetcode_剑指OfferII043_往完全二叉树添加节点;

import 剑指offer.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
}
class CBTInserter {
    private TreeNode tree;
    private Queue<TreeNode> needChild;

    public CBTInserter(TreeNode root) {
        this.tree = root;
        Queue<TreeNode> queue = new LinkedList<>();
        needChild = new LinkedList<>();

        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode poll = queue.poll();
            if(poll.left != null) {
                queue.offer(poll.left);
            }
            if(poll.right != null) {
                queue.offer(poll.right);
            }
            if(!(poll.left!=null&&poll.right!=null)){
                needChild.offer(poll);
            }
        }
    }

    public int insert(int v) {
        TreeNode child = new TreeNode(v);
        TreeNode cur = needChild.peek();
        if(cur.left==null){
            cur.left = child;
        }else {
            cur.right = child;
            needChild.poll();
        }
        needChild.offer(child);
        return cur.val;
    }

    public TreeNode get_root() {
        return tree;
    }
}