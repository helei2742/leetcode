package 剑指offer.leetcode_剑指Offer20_表示数值的字符串;

class Solution {
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) return false;
        //去掉首位空格
        s = s.trim();
        //是否出现数字
        boolean numFlag = false;
        //是否出现小数点
        boolean dotFlag = false;
        boolean eFlag = false;

        for (int i = 0; i < s.length(); i++) {

            if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                numFlag = true;
            }else if(s.charAt(i) == '.' && !dotFlag && !eFlag){
                dotFlag = true;
            }else if((s.charAt(i)=='e'||s.charAt(i)=='E')&&!eFlag&&numFlag){
                eFlag = true;
                numFlag = false;
            }else if((s.charAt(i)=='-'||s.charAt(i)=='+')&& (i == 0 || s.charAt(i - 1) == 'e' || s.charAt(i - 1) == 'E')){

            }else {
                return false;
            }
        }
        return numFlag;
    }
}
