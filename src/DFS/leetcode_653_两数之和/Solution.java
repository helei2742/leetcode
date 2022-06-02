package DFS.leetcode_653_两数之和;

import DFS.TreeNode;

import java.util.HashSet;

class Solution {

    public boolean findTarget(TreeNode root, int k) {
        HashSet<Integer> set = new HashSet<>();

        return find(root,k,set);
    }

    private boolean find(TreeNode root, int k, HashSet<Integer> set){
        if(root == null) return false;
        if(set.contains(k- root.val))
            return true;
        set.add(root.val);
        return find(root.left, k , set) || find(root.right,k,set);
    }
}
