package DFS.leetcode_17_电话号码的字母组合;

import java.util.List;

public class test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> strings = solution.letterCombinations("");
        strings.forEach((s)->{
            System.out.println("1"+s);
        });
    }
}
