package 并查集.leetcode_839_相似字符串;

public class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] strings = {"tars","rats","arts","star"};
        int i = solution.numSimilarGroups(strings);
        System.out.println(i);
        boolean twoStringAlike = solution.isTwoStringAlike("rats", "tars");
        boolean twoStringAlike1 = solution.isTwoStringAlike("tars", "star");
        boolean twoStringAlike2 = solution.isTwoStringAlike("arts", "star");
        System.out.println(twoStringAlike);
        System.out.println(twoStringAlike1);
        System.out.println(twoStringAlike2);
    }
}
