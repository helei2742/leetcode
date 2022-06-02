package DFS.leetcode_623_在二叉树中增加一行;

import DFS.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        Queue<TreeNode> queue = new LinkedList<>();
        if(depth == 1){
            return new TreeNode(val, root, null);
        }

        int cutDep = 0;
        queue.add(root);
        while (queue.size() != 0) {
            int size = queue.size();
            List<TreeNode> nodeList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                nodeList.add(node);
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
            if(++cutDep == depth-1){
                for (TreeNode node : nodeList) {
                    TreeNode oleft = node.left;
                    TreeNode oright = node.right;
                    node.left = new TreeNode(val, oleft, null);
                    node.right = new TreeNode(val, null, oright);
                }
            }
        }
        return root;
    }

}
