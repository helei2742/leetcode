package DFS.leetcode_606_根据二叉树创建字符串;

import DFS.TreeNode;

public class Solution {
    public String tree2str(TreeNode root) {
        if (root == null) return "";
        String res = root.val +"";
        String rString = tree2str(root.right);
        String lString = tree2str(root.left);
        if(lString.length() != 0 && rString.length() == 0) {
            res += "(" + lString + ")()";
        }
        if(lString.length() ==0 && rString.length() != 0) {
            res += "()(" + rString + ")";
        }
        if(lString.length() !=0 && rString.length() != 0) {
            res += "(" + lString + ")"+"(" + rString + ")";
        }
        return res;
    }
}
