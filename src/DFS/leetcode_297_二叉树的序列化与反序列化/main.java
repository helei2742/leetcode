package DFS.leetcode_297_二叉树的序列化与反序列化;

import DFS.TreeNode;
import DFS.leetcode_235_二叉搜索树的最近公共祖先.Solution;

import java.util.List;

public class main {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);

        root.right = new TreeNode(8);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        Codec codec = new Codec();
        String serialize = codec.serialize(root);
        codec.deserialize(serialize);
    }
}
