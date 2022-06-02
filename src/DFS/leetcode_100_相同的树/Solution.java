package DFS.leetcode_100_相同的树;

public class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if((p == null && q != null)||(p != null && q == null))
            return false;
        if(p == null&& q==null) return true;
        boolean left = isSameTree(p.left, q.left);

        if(p.val != q.val) return false;

        boolean right = isSameTree(p.right, q.right);
        return left && right;
    }
}
