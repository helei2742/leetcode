package DFS.leetcode_111_二叉树的最小深度;

import DFS.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    class QueueNode{
        public int dep;
        public TreeNode root;
        QueueNode(TreeNode root, int dep){
            this.dep = dep;
            this.root = root;
        }
    }

    public int minDepth(TreeNode root) {
//        return dfs(root);
        return bfs(root);
    }
    private int dfs(TreeNode root) {
        if(root == null) {
            return 0;
        }
        if(root.left == null && root.right ==null)
            return 1;
        int min = Integer.MIN_VALUE;
        if(root.left != null){
            min = Math.min(min, dfs(root.left));
        }
        if(root.right != null){
            min = Math.min(min, dfs(root.right));
        }
        return min + 1;
    }

    private int bfs(TreeNode root){
        if(root == null) return 0;

        Queue<QueueNode> queue = new LinkedList<>();
        queue.add(new QueueNode(root, 1));
        while(!queue.isEmpty()){
            QueueNode head = queue.poll();
            TreeNode node =  head.root;
            if(node.left == null && node.right == null){
                return head.dep;
            }
            if(node.left != null){
                queue.add(new QueueNode(node.left, head.dep + 1));
            }
            if(node.right != null){
                queue.add(new QueueNode(node.right, head.dep + 1));
            }
        }
        return 0;
    }
}
