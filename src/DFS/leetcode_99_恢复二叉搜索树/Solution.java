package DFS.leetcode_99_恢复二叉搜索树;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public void recoverTree(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);

        int[] needChange = findTwoError(list);

        recover(root, needChange[0], needChange[1], 2);
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if(root == null) return ;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    private int [] findTwoError(List<Integer> list) {
        int x = -1, y = -1;
        for (int i = 1; i < list.size(); i++) {
            if(list.get(i-1) >= list.get(i)) {
                y = list.get(i);
                if(x == -1) {
                    x = list.get(i-1);
                }else {
                    break;
                }
            }
        }
        return new int[]{x,y};
    }

    private void recover(TreeNode root, int x, int y, int count) {
        if(root != null) {
            if (root.val == x || root.val == y) {
                root.val = root.val == x ? y : x;
                if (--count == 0) {
                    return;
                }
            }
            recover(root.right, x, y, count);
            recover(root.left, x, y, count);
        }
    }
}
