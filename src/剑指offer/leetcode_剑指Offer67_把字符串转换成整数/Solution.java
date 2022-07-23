package 剑指offer.leetcode_剑指Offer67_把字符串转换成整数;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int strToInt(String str) {
        Automaton automaton = new Automaton();
        for (int i = 0; i < str.length(); i++) {
            automaton.get(str.charAt(i));
        }

        return (int) (automaton.sign*automaton.ans);
    }

    class Automaton{
        public int sign = 1;
        private long ans = 0;
        private String state = "start";
        private Map<String, String[]> table = new HashMap<String, String[]>() {{
            put("start", new String[]{"start", "signed", "in_number", "end"});
            put("signed", new String[]{"end", "end", "in_number", "end"});
            put("in_number", new String[]{"end", "end", "in_number", "end"});
            put("end", new String[]{"end", "end", "end", "end"});
        }};

        public void get(char c){
            this.state = table.get(state)[getType(c)];
            if("in_number".equals(this.state)){
                ans = ans * 10 + (c-'0');
                ans = sign == 1 ? Math.min(ans, (long) Integer.MAX_VALUE) : Math.min(ans, -(long) Integer.MIN_VALUE);
            }else if("signed".equals(this.state)){
                sign = (c=='+')?1:-1;
            }
        }

        private int getType(char c){
            if(c==' ') return 0;
            if(c=='+'||c=='-') return 1;
            if(Character.isDigit(c)) return 2;
            return 3;
        }
    }
}