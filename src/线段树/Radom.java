 package 线段树;

import java.util.*;

public class Radom {
    public static void main(String[] args) {
        String[] name = {"何磊", "赵文野", "项羽", "陶群其"};
        String[] work = {"ppt", "演讲", "拓展1", "写材料"};
        Map<String, Integer> map = new HashMap<>();
        for (String s : name) {
            map.put(s, new Random().nextInt(100));
        }
        Arrays.sort(name, (String a, String b ) -> {
            return map.get(a) - map.get(b);
        });
        int i = 0;
        for (String s : name) {
            System.out.println(s + work[i++]);
        }
    }
}
