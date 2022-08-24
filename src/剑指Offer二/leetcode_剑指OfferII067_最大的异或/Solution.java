package 剑指Offer二.leetcode_剑指OfferII067_最大的异或;

class Solution {
    class Node {
        public Node[] child = new Node[2];
    }
    private Node root;

    private void insert(int num) {
        Node cur = root;
        for (int i = 31; i >= 0; i--) {
            int idx = (num>>i) & 1;
            if(cur.child[idx] == null) {
                cur.child[idx] = new Node();
            }
            cur = cur.child[idx];
        }
    }

    private int getVal(int num) {
        Node cur = root;
        int res = 0;
        for (int i = 31; i >= 0; i--) {
            int idx = (num>>i) & 1;
            int otherIdx = 1 - idx;
            if(cur.child[otherIdx] != null) {
                res |= otherIdx<<i;
                cur = cur.child[otherIdx];
            }else {
                res |= idx<<i;
                cur = cur.child[idx];
            }
        }
        return res;
    }
    public int findMaximumXOR(int[] nums) {
        root = new Node();
        int res = 0;
        for (int num : nums) {
            insert(num);
            int other = getVal(num);
            res = Math.max(res, other ^ num);
        }
        return res;
    }
}
