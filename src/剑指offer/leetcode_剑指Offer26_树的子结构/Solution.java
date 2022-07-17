package 剑指offer.leetcode_剑指Offer26_树的子结构;

public class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(A==null||B==null) return false;

        return isSubStructure(A.left, B)|isSubStructure(A.right,B)|sub(A,B);
    }
    // A为根是否包含B为根
    private boolean sub(TreeNode A, TreeNode B){
        if(B==null) return true;
        if(A==null||A.val != B.val) return false;
        return sub(A.left, B.left) && sub(A.right, B.right);
    }
}
