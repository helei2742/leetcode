package 图.leetcode_685_冗余连接2;

class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        UnionFind unionFind = new UnionFind(n);
        int[] parent = new int[n+1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
        int count = 0;
        int cycle = -1;
        int conflict = -1;
        for (int[] edge : edges) {
            int n1 = edge[0];
            int n2 = edge[1];
            if(parent[n2] == n2){
                //只有一个父亲点
                parent[n2] = n1;
                if(unionFind.findF(n1) == unionFind.findF(n2)){
                    cycle = count;
                }else {
                    unionFind.union(n1, n2);
                }
            }else{
                //有两个父亲节点，用冲突
                conflict = count;
            }
            count++;
        }
        System.out.println(conflict);
        System.out.println(cycle);
        if(conflict<0){
            return new int[]{edges[cycle][0],edges[cycle][1]};
        }else {
            if(cycle>=0){
                return new int[]{parent[edges[conflict][1]], edges[conflict][1]};
            }else {
                return new int[] {edges[conflict][0],edges[conflict][1]};
            }
        }
    }
    class UnionFind{
        private int[] father;
        UnionFind(int n){
            this.father = new int[n+1];
            for (int i = 0; i <= n; i++) {
                father[i] = i;
            }
        }

        public int findF(int x){
            if(father[x] != x) return father[x] = findF(father[x]);
            return x;
        }
        public boolean union(int a, int b){
            int fA = findF(a);
            int fB = findF(b);
            if(fA == fB) return false;
            father[fA] = fB;
            return true;
        }
    }
}