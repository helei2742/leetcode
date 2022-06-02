package DFS.leetcode_297_二叉树的序列化与反序列化;

import DFS.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        String encode = encode2(root);
       // String encode2 = encode(root, "");
        return encode;
    }

/*    private String encode(TreeNode root, String val) {
        if(root == null) {
            return val + "None.";
        }
        val += String.valueOf(root.val) + '.';
        val = encode(root.left, val);
        val = encode(root.right, val);
        return val;
    }*/

    private String encode2(TreeNode root) {
        if(root == null) {
            return "None.";
        }
        String left = encode2(root.left);
        String right = encode2(root.right);
        return String.valueOf(root.val) + '.' + left + right;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] split = data.split("\\.");
        List<String> strings = new LinkedList<>(Arrays.asList(split));
        TreeNode decode = decode(strings);
        return decode;
    }

    private TreeNode decode(List<String> data) {
        if("None".equals(data.get(0))){
            data.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(data.get(0)));
        data.remove(0);
        root.left = decode(data);
        root.right = decode(data);
        return root;
    }
}