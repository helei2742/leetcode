package DFS.leetcode_655_输出二叉树;

import DFS.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<List<String>> printTree(TreeNode root) {
        int height = getHeight(root);
        String[][] res = new String[height][(1 << height) - 1];
        for (String[] re : res) {
            Arrays.fill(re, "");
        }

        dfs(root,res,0,0,res[0].length);
        List<List<String>> ans = new ArrayList<>();
        for (String[] s : res) {
            ans.add(Arrays.asList(s));
        }
        return ans;
    }
    private int getHeight(TreeNode root){
        if(root==null) return 0;
        return Math.max(getHeight(root.right), getHeight(root.left)) + 1;
    }
    private void dfs(TreeNode root,String[][] res, int h, int l, int r){
        if(root == null) return;
        res[h][(l+r)/2] = "" + root.val;
        dfs(root.left,res,h+1,l,(l+r)/2);

        dfs(root.right,res,h+1,(l+r+1)/2,r);
    }
}
