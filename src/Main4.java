import java.util.Scanner;


class Solution {
    public  int dfs(int index, int n, boolean[][] row, boolean[][] col){
        int x = index/2;
        int y = index%2;

        if(x==1&&y==1){
            return 1;
        }

        int res = 1;
        for (int i = index+1; i < 2*2; i++) {
            for (int k = 0; k < n; k++) {
                if(!row[i/2][k]&&!col[i%2][k]){
                    row[i/2][k] = true;
                    col[i%2][k] = true;
                    res *= dfs(i,n,row,col);
                    row[i/2][k] = false;
                    col[i%2][k] = false;
                }
            }
        }
        return res;
    }
}
public class Main4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int r = new Solution().dfs(0,n,new boolean[2][n],new boolean[2][n]);
        System.out.println(r+1);
    }
}
