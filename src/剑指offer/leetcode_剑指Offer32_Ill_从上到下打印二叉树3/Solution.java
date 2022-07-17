package 剑指offer.leetcode_剑指Offer32_Ill_从上到下打印二叉树3;

import org.junit.platform.commons.util.CollectionUtils;

import java.util.*;

public class Solution {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();

        if(root!=null)
            queue.add(root);

        List<List<Integer>> ans = new ArrayList<>();
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> l = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                l.add(poll.val);
                if(poll.left!=null)
                    queue.offer(poll.left);
                if(poll.right!=null)
                    queue.offer(poll.right);
            }
            if(++count%2 == 0)
                Collections.reverse(l);
            ans.add(l);
        }
        return ans;
    }
}
