package 并查集.leetcode_839_相似字符串;

public class Solution {

    public int numSimilarGroups(String[] strs) {

        int size = strs.length;
        UnionFind unionFind = new UnionFind(size);

        for(int i = 0; i < size; i++) {
            for(int j = i + 1; j < size; j++){
                if(isTwoStringAlike(strs[i], strs[j])) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.getCount();
    }

    public boolean isTwoStringAlike(String str1, String str2) {
        if(str1.length() != str2.length()) return false;
        int len = str1.length();
        int num = 0;
        for (int i = 0; i < len; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                num++;
                if (num > 2) {
                    return false;
                }
            }
        }
        return true;
    }

    class UnionFind {
        private int[] father;
        private int count;
        UnionFind(int n) {
            father = new int[n];
            count = n;
            for (int i = 0; i < n; i++) {
                father[i] = i;
            }
        }
        public int findF(int x) {
            if(x != father[x]) father[x] = findF(father[x]);
            return father[x];
        }
        public void union(int a, int b) {
            int fA = findF(a);
            int fB = findF(b);
            if(fA == fB) return;
            count--;
            father[fA] = fB;
        }
        public int getCount() {
            return this.count;
        }
    }

}
