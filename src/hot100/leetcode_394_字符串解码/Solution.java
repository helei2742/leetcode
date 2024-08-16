package hot100.leetcode_394_字符串解码;

class Solution {
    public String decodeString(String s) {
        int len = s.length();

        return dfs(s.toCharArray(), 0, len-1);
    }

    private String dfs(char[] chars, int start, int end) {

        StringBuilder sb = new StringBuilder();

        int idx = start;
        while(idx <= end) {
            char aChar = chars[idx];
            if(aChar >= '0' && aChar <= '9'){
                int count = 0;
                while(chars[idx] != '['){
                    count = count * 10 + (chars[idx]-'0');
                    idx++;
                }

                int l = idx;

                int leftCount = 1, rightCount = 0;
                while(true){
                    if(chars[idx] == '[') {
                        leftCount++;
                    }
                    if(chars[idx] == ']'){
                        rightCount++;
                    }
                    if(rightCount == leftCount) break;
                    idx++;
                }


                String dS = dfs(chars, l+1, idx-1);
                for (int i = 0; i < count; i++) {
                    sb.append(dS);
                }
            }else if(aChar != '[' && aChar !=']'){
                sb.append(chars[idx++]);
            }else {
                idx++;
            }
        }
        return sb.toString();
    }
}
