package DFS.leetcode_529_扫雷游戏;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        char[][] a =
                {{'E', 'E', 'E', 'E', 'E'},
                        {'E', 'E', 'M', 'E', 'E'},
                        {'E', 'E', 'E', 'E', 'E'},
                        {'E', 'E', 'E', 'E', 'E'}};
        char[][] b =
                {{'B', '1', 'E', '1', 'B'},
                        {'B', '1', 'M', '1', 'B'},
                        {'B', '1', '1', '1', 'B'},
                        {'B', 'B', 'B', 'B', 'B'}};
        Solution solution = new Solution();
//        solution.updateBoard(a, new int[]{3,0});


        solution.updateBoard(b, new int[] {1,2});
/*        Method getSimple = solution.getClass().getDeclaredMethod("getSimple", char[][].class, int.class, int.class);
        getSimple.setAccessible(true);
        Object invoke = getSimple.invoke(solution, a, 0,1);
        System.out.println((char) invoke);*/

        for (char[] chars : b) {
            System.out.println(chars);
        }
    }
}
