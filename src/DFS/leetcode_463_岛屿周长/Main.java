package DFS.leetcode_463_岛屿周长;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int [][] a = {{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};
        int i = solution.islandPerimeter(a);
        System.out.println(i);
    }
}
