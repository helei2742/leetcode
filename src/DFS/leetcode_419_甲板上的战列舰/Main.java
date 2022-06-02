package DFS.leetcode_419_甲板上的战列舰;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] b = {{'X','.','.','X'},{'.','.','.','X'},{'.','.','.','X'}};
        char[][] c=     {{'.','X','.','.','X'}
                        ,{'.','X','.','.','X'}
                        ,{'.','.','.','.','X'}
                        ,{'X','.','X','X','.'}
                        ,{'X','.','.','.','X'}};
        for (int j = 0; j < 5; j++) {
            System.out.println(c[j][0]+" "+c[j][1]+" "+c[j][2]+" "+c[j][3]+" "+c[j][4]+" ");
        }
        int i= solution.countBattleships(c);

        for (int j = 0; j < 5; j++) {
            System.out.println(c[j][0]+" "+c[j][1]+" "+c[j][2]+" "+c[j][3]+" "+c[j][4]+" ");
        }
        System.out.println(i);
    }
}
